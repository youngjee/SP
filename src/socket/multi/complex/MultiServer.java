package socket.multi.complex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiServer implements Runnable {

	public static void main(String[] args) {

		int portNumber = 30000;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
			ExecutorService es = Executors.newFixedThreadPool(5);
			while (true) {
				es.submit(new MultiServer(serverSocket.accept()));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private Socket socket;
	private int clientNum;

	public MultiServer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		PrintWriter out = null;
		BufferedReader in = null;

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String echo;
			while ((echo = in.readLine()) != null) {
				String message;
				if (echo.equals("file")) {
					message = "파일전송중...";
				} else if (echo.equals("exec")) {
					message = "exe실행중...";
				} else {
					message = "입력오류!";
				}
				System.out.println(message);
				out.println(message);
				out.flush();

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}

	

}