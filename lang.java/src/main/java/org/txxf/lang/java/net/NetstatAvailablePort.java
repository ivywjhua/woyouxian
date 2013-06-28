package org.txxf.lang.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.txxf.lang.java.utils.MyExceptionUtils;
import org.txxf.lang.java.utils.MyProcessUtils;
import org.txxf.lang.java.utils.MyStringUtils;

public class NetstatAvailablePort {

	private static final String COLON = ":";

	public boolean portAvailable(int port) {
		String[] commands = new String[3];
		commands[0] = "/bin/sh";
		commands[1] = "-c";
		commands[2] = "netstat -an | grep " + String.valueOf(port);
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(commands);

		InputStream inStream = null;
		try {
			Process process = pb.start();
			MyProcessUtils.waitForProcess(process, 10, 500);
			inStream = process.getInputStream();
			String netout = IOUtils.toString(inStream);
			return StringUtils.equals(StringUtils.EMPTY, netout) ? true : false;
		} catch (IOException e) {
			throw MyExceptionUtils.uncheckException(e);
		} catch (TimeoutException e) {
			throw MyExceptionUtils.uncheckException(e);
		} finally {
			IOUtils.closeQuietly(inStream);
		}
	}

	public Set<Integer> notAvaiPorts() {
		String[] commands = new String[2];
		commands[0] = "netstat";
		commands[1] = "-an";

		ProcessBuilder pb = new ProcessBuilder();
		pb.command(commands);

		try {
			Process process = pb.start();
			process.waitFor();
			String netout = IOUtils.toString(process.getInputStream());
			StringTokenizer rowst = new StringTokenizer(netout,
					MyStringUtils.LINE_SEPARATOR);
			rowst.nextElement();
			rowst.nextElement();

			Set<Integer> notAvaiPortSet = new HashSet<Integer>();
			while (rowst.hasMoreElements()) {
				String row = (String) rowst.nextElement();
				StringTokenizer wordst = new StringTokenizer(row);
				wordst.nextElement();
				wordst.nextElement();
				wordst.nextElement();

				String addr = (String) wordst.nextElement();
				if (addr.contains(COLON)) {
					String port = addr.substring(addr.lastIndexOf(COLON) + 1);
					notAvaiPortSet.add(Integer.valueOf(port));
				}
			}
			return notAvaiPortSet;
		} catch (IOException e) {
			throw MyExceptionUtils.uncheckException(e);
		} catch (InterruptedException e) {
			throw MyExceptionUtils.uncheckException(e);
		}
	}

}
