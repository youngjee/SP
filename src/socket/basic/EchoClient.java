package socket.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String hostName = "127.0.0.1";

		int portNumber = 3000;

		Socket socket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

		String userInput;
		
		while ((userInput = stdIn.readLine()) != null) {
			if("Q".equals(userInput))
				break;
			out.println(userInput);
			out.flush();
			System.out.println("echo: " + in.readLine());
		}

		in.close();
		out.close();
		socket.close();
	}
}
