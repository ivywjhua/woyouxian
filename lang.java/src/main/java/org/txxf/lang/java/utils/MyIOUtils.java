package org.txxf.lang.java.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyIOUtils {

	public static String getStringFromStream(InputStream in) throws IOException {
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = bufReader.readLine()) != null) {
				sb.append(line + MyStringUtils.LINE_SEPARATOR);
			}
		} finally {
			MyIOUtils.close(bufReader);
		}
		return sb.toString();
	}
	
	public static void close(Closeable obj) {
		if (null != obj) {
			try {
				obj.close();
			} catch (IOException e) {
				// do nothing
			}
		}
	}
}
