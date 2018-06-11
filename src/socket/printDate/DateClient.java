package socket.printDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DateClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String hostName = "127.0.0.1";

		int portNumber = 3000;

		Socket socket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

		String serverInput;
		
		/*out.println("start");
		out.flush();*/
		
		while ((serverInput = in.readLine()) != null) {
			System.out.println(serverInput);
		}

		in.close();
		out.close();
		socket.close();
	}
}
