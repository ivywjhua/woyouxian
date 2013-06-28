package org.txxf.lang.java.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class SocketAvailablePort {

	public static final Random random = new Random();

	public static boolean portAvailable(int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setReuseAddress(true);
		} catch(IOException e) {
			// do nothing
			return false;
		} finally {
			if (null != serverSocket && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch(IOException e) {
					// do nothing
				}
			}
		}

		Socket socket = null;
		try {
			socket = new Socket("localhost", port);
		} catch (UnknownHostException e) {
			// do nothing
		} catch(IOException e) {
			return true;
		} finally {
			if (null != socket && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}

		return false;
	}

	public static int findAvailablePort() {
		int port = 0;
		do {
			port = random.nextInt(10000) + 30000;
		} while(portAvailable(port));

		try {
			Thread.sleep(200);
		} catch(Exception e) {
			// do nothing
		}
		return port;
	}

	public static int findAvailablePort2() {
		int port = 0;
		do {
			port = random.nextInt(10000) + 20000;
		} while(!portAvailable(port));
		return port;
	}

	public static void main(String[] args) {
		int port = 0;
		for (int i=0; i < 10; ++i) {
			port = findAvailablePort2();
			System.out.println(port + " port available:");
		}

		System.out.println("Thread sleep over");
	}

}



