package main;

import java.net.Socket;
import java.net.ServerSocket;
import java.lang.Runnable;
import java.io.IOException;
import java.lang.Thread;

import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.TreeMap;

class HTTP {
	public static void main(String args[]) {
		try {
			new HTTP();
		} catch (IOException e) {
		}
	}

	public HTTP() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Listening for connection on port 8080 ....");
		while(true) {
			Socket socket = serverSocket.accept();

			Thread thread = new Thread(new ServerThread(socket));
			thread.run();
		}
	}
}



