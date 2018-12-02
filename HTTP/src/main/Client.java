package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

    public Client() throws Exception {

	Socket socket = new Socket("localhost", 8080);
	String root = "src/folder/";
	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	String request = "";
	String response = "";
	String filepath = "src/folder/b.txt";
	
	// GET
	request = "GET " + filepath + " HTTP/1.1 \r\n";
	out.println(request);
	System.out.println(request);
	response = in.readLine() + "\r\n";
	response += in.readLine();
	System.out.println(response);
	// DELETE
	request = "DELETE " + filepath + " HTTP/1.1 \r\n";
	out.println(request);
	System.out.println(request);
	response = in.readLine()+ "\r\n";
	response += in.readLine();
	System.out.println(response);
	// POST
	request = "POST " + filepath + " HTTP/1.1 \r\n";
	out.println(request);
	System.out.println(request);
	response = in.readLine()+ "\r\n";
	response += in.readLine();
	System.out.println(response);
	// PUT
	request = "PUT " + filepath + " HTTP/1.1 \r\n";
	out.println(request);
	System.out.println(request);
	response = in.readLine()+ "\r\n";
	response += in.readLine();
	System.out.println(response);
	// HEAD
	request = "HEAD " + filepath + " HTTP/1.1 \r\n";
	out.println(request);
	System.out.println(request);
	response = in.readLine();
	System.out.println(response);

	
	request = "end end ";
	out.println(request);
	System.out.println("kraj");
	socket.close();

    }

    public static void main(String[] args) {
	try {
	    new Client();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
