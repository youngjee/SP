package socket.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

//server로 소켓통신하고 QUIT을 입력하면 close
public class ConsoleServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		CardServer cardSever = new CardServer();
		Thread thread = new Thread(cardSever);
		thread.start();
		
		Scanner scanner = new Scanner(System.in);
		
		String line;
		while ((line = scanner.nextLine()) != null) {
			if (line.equals("QUIT")) {
				cardSever.close();
				System.out.println("Socket 종료");
				break;
			}
		}
		
		scanner.close();
	}
}

class CardServer implements Runnable{
	
	private ServerSocket listener =null;
	private Socket socket = null;

	@Override
	public void run() {
		
		PrintWriter out = null;
		BufferedReader in =null;
		
		try {
			listener = new ServerSocket(3000);
			socket = listener.accept();
			
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String echo;

	        while ((echo = in.readLine()) != null) {

	            System.out.println("echo: " + echo);

	            out.println(echo);

	            out.flush();

	        }
			//socket통신하기
			
		} catch (IOException e) {
			//do nothing
		}finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		
		
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
