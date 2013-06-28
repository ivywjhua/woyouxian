package org.txxf.lang.java.utils;

public class MyExceptionUtils {

	public static RuntimeException uncheckException(Throwable t) {
		return new RuntimeException(t);
	}

	public static RuntimeException uncheckException(String msg, Throwable t) {
		return new RuntimeException(msg, t);
	}
}
