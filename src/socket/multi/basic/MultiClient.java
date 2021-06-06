package socket.multi.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {

		String hostName = "127.0.0.1";
		int portNumber = 30000;
		Socket socket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput;

		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			out.flush();
			System.out.println("echo: " + in.readLine());
		}

		in.close();
		out.close();
		socket.close();

	}
}
