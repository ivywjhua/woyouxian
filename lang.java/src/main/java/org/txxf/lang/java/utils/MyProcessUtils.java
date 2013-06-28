package org.txxf.lang.java.utils;

import java.util.concurrent.TimeoutException;

public class MyProcessUtils {

	public static int waitForProcess(Process p, int iteration, int delay)
			throws TimeoutException {
		for (int i = 0; i < iteration; i++) {
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				// ignore
			}
			try {
				return p.exitValue();
			} catch (IllegalThreadStateException e) {
				// ignore
			}
		}
		
		// process timeout, destroy process and throw out exception
		p.destroy();
		throw new TimeoutException("Process did not complete and had to be terminated");
	}
	
}
