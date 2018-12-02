package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TreeMap;

class ServerThread implements Runnable {
    Socket socket;
    String filepath = "src/folder";
    File f;
    TreeMap<String, String> arguments = new TreeMap<String, String>();

    public ServerThread(Socket socket) {
	this.socket = socket;
    }

    public void run() {
	try {
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	    String request = in.readLine();

	    String[] requestParams = request.split(" ");

	    // jer zahtev treba da ima 3 dela
	    if (requestParams.length != 3) {
		socket.close();
		return;
	    }

	    if (!requestParams[2].equals("HTTP/1.1")) {
		socket.close();
		return;
	    }

	    String line;

	    while ((line = in.readLine()) != null && !line.equals("")) {
		System.out.println("<>" + line + "<>");

		String[] argument = line.split(":");

		this.arguments.put(argument[0].toLowerCase(), argument[1]);
	    }
	    
	    filepath+= requestParams[1];
	    f = new File(filepath);
	    switch (requestParams[0]) {
	    case ("GET"):
		if (f.exists()) {
		    StringBuilder contentBuilder = new StringBuilder();
		    try {
		        BufferedReader br = new BufferedReader(new FileReader(f));
		        String str;
		        while ((str = br.readLine()) != null) {
		            contentBuilder.append(str);
		        }
		        br.close();
		    } catch (IOException e) {
		    }
		    String response = contentBuilder.toString();
		    out.println("HTTP/1.1 200 OK");
		    out.println("Content-Length: " + response.length() + "\n");
		    out.println(response);
		} else {
		    String response = "Not found";

		    out.println("HTTP/1.1 404 Not Found");
		    out.println("Content-Length: " + response.length() + "\n");
		    out.println(response);
		}

		break;
	    default:
		String response = "Method not allowed";

		out.println("HTTP/1.1 405 Method not allowed");
		out.println("Content-Length: " + response.length() + "\n");
		out.println(response);

		socket.close();
	    }
	} catch (IOException e) {

	}
    }
}