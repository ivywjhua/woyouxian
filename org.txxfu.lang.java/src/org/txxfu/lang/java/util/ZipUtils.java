package org.txxfu.lang.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

	public static void unzipFromStream(InputStream inStream, String targetFolder) {
		ZipInputStream zipInputStream = null;
		FileOutputStream outputStream = null;
		try {
			System.out.println(new Date() + "Started unzip: " + targetFolder);
			// 1. Use ZipInputStream to parse the zip
			zipInputStream = new ZipInputStream(inStream);
			ZipEntry zipEntry = null;
			outputStream = null;
			// 2. Parse and unzip each zip entry from the zip input stream
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				// 2.1 If the zip entry is folder, create the folder
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File file = new File(targetFolder + File.separator + name);
					file.mkdir();
				}
				// 2.2 If the zip entry is file, unzip it to file
				else {
					File file = new File(targetFolder + File.separator
							+ zipEntry.getName());
					file.createNewFile();
					outputStream = new FileOutputStream(file);
					// Get the size of next zip entry from the input stream
					int size = (int) zipEntry.getSize();
					byte[] bytes = new byte[size];
					int offset = 0;
					int chunk = 0;
					// Then read corresponding size bytes from the input stream,
					// and write it to file
					while (offset < size) {
						chunk = zipInputStream.read(bytes, offset, size
								- offset);
						if (chunk == -1) {
							break;
						}
						offset += chunk;
					}
					outputStream.write(bytes);
				}
			}
			outputStream.flush();
			System.out.println(new Date() + "Completed unzip: " + targetFolder);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipInputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			try {
				String srcPath = "d:/wjh/temp/wlp.zip";
				fis = new FileInputStream(srcPath);
				doUnzip(fis);
			} finally {
				fis.close();
			}
		} catch (FileNotFoundException e) {
			throw ExceptionUtils.uncheckException(e);
		} catch (IOException e) {
			throw ExceptionUtils.uncheckException(e);
		}

	}

	private static void doUnzip(InputStream in) {
//		String eclipseHomeFolder = System.getProperty("user.dir");
		String eclipseHomeFolder = "d:/wjh/temp";

		// Unzip liberty only if the target wlp folder does not exist
		File folder = new File(eclipseHomeFolder + File.separator + "wlp");
		if (!folder.exists()) {
			// Get the wlp.zip from plugin jar as resource stream
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			InputStream in = loader.getResourceAsStream("libs" + File.separator
//					+ "wlp.zip");
			// Unzip wlp (Liberty)
			unzipFromStream(in, eclipseHomeFolder);
			setPermission(eclipseHomeFolder + File.separator + "wlp");
		}
	}

	private static void setPermission(String wlpPath) {
		String[] permissionFileName = new String[]{"server", "isadc", "productInfo", "securityUtility"};
		for (String fileName : permissionFileName) {
			File executableFile = new File(wlpPath + File.separator + "bin" + File.separator + fileName);
			executableFile.setExecutable(true);
		}
	}
}
