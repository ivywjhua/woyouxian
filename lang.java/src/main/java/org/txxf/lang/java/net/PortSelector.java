package org.txxf.lang.java.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.txxf.lang.java.utils.MyExceptionUtils;

public class PortSelector {

	private List<Integer> netstat = new ArrayList<Integer>();
	
	public List<Integer> getNetstat() {
		return netstat;
	}

	public void callNetstat() {
		try {
			ByteArrayOutputStream netout = new ByteArrayOutputStream();
			ByteArrayOutputStream neterr = new ByteArrayOutputStream();

			String systemOS = System.getProperty("os.name");
			String localaddr = "";

			ArrayList<String> cmd = new ArrayList<String>();
			if (systemOS.equals("z/OS")) {
				cmd.add("onetstat");
				cmd.add("-a");
			} else if (systemOS.equalsIgnoreCase("OS/400")) {
				cmd.add("qsh");
				cmd.add("-c");
				cmd.add("netstat");
			} else {
				cmd.add("netstat");
				cmd.add("-n");
				cmd.add("-a");
			}

			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process p = pb.start();

			MyReader out = new MyReader(p.getInputStream(), netout);
			MyReader err = new MyReader(p.getErrorStream(), neterr);
			out.start();
			err.start();

			p.waitFor();
			out.join();
			err.join();

			String output = netout.toString();
			if (output.trim().length() == 0) {
				throw new Exception("Did not receive any output from netstat");
			}
			StringTokenizer stok = new StringTokenizer(output, "\r\n");
			stok.nextElement();
			stok.nextElement();
			if (systemOS.equals("z/OS")) {
				stok.nextElement();
			}
			while (stok.hasMoreElements()) {
				String s = (String) stok.nextElement();
				if (systemOS.equals("z/OS")) {
					Pattern ptrn = Pattern
							.compile("\\s\\sLocal\\sSocket:.*?\\.\\.(\\d*).*");
					Matcher m = ptrn.matcher(s);
					if (m.find()) {
						localaddr = m.group(1);
						try {
							int port = Integer.parseInt(localaddr);
							if (!this.netstat.contains(Integer.valueOf(port)))
								this.netstat.add(Integer.valueOf(port));
						} catch (NumberFormatException e) {
						}
					}
				} else {
					StringTokenizer stok2 = new StringTokenizer(s);

					if (stok2.countTokens() > 1) {
						stok2.nextElement();

						if (!System.getProperty("os.name").toLowerCase()
								.contains("windows")) {
							if (stok2.countTokens() > 2) {
								stok2.nextElement();
								stok2.nextElement();
							}

						} else {
						}
						localaddr = (String) stok2.nextElement();
						if (localaddr.indexOf(":") != -1)
							localaddr = localaddr.substring(localaddr
									.lastIndexOf(":") + 1);
						else if (localaddr.lastIndexOf(".") != -1) {
							localaddr = localaddr.substring(localaddr
									.lastIndexOf(".") + 1);
						}
						try {
							int port = Integer.parseInt(localaddr);
							if (!this.netstat.contains(Integer
									.valueOf(port)))
								this.netstat.add(Integer.valueOf(port));
						} catch (NumberFormatException e) {
							// do nothing
						}
					}
				}
			}
		} catch (Exception e) {
			throw MyExceptionUtils.uncheckException(
					"Error trying to parse netstat output", e);
		}
	}

	private class MyReader extends Thread {
		private OutputStream out;
		private InputStream in;

		public MyReader(InputStream in, OutputStream out) {
			this.out = out;
			this.in = in;
		}

		public void run() {
			PrintWriter writer = new PrintWriter(this.out, true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.in));
			try {
				String line = null;
				while ((line = reader.readLine()) != null)
					writer.println(line);
			} catch (Exception e) {
			}
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
				}
		}
	}
}