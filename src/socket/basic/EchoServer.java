package socket.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 전달한 메시지 출력하는 실습

public class EchoServer {
	public static void main(String[] args) throws IOException {
		int portNum = 3000;
		
		ServerSocket serverSocket = new ServerSocket(portNum);
		Socket socket = serverSocket.accept();
		
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String echo;

        while ((echo = in.readLine()) != null) {

            System.out.println("echo: " + echo);

            out.println(echo);

            out.flush();

        }

        in.close();
        out.close();
        socket.close();
        serverSocket.close();
	}
}
