package org.txxfu.lang.java.util;

public class ExceptionUtils {

	public static RuntimeException uncheckException(String msg, Throwable e) {
		return new RuntimeException(msg, e);
	}
	
	public static RuntimeException uncheckException(Throwable cause) {
		return new RuntimeException(cause);
	}
}
