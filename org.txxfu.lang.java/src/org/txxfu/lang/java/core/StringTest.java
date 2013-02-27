package org.txxfu.lang.java.core;

import java.io.File;

public class StringTest {

	public static void main(String[] args) {
		String str = "org.txxfu.lang.java.core";
//		String subDir = str.replaceAll("\\.", "\\\\");
		String subDir = str.replace(".", File.separator);
		System.out.println(subDir);
	}
}
