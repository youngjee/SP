package socket.printDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateServer {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		int portNum = 3000;

		ServerSocket serverSocket = new ServerSocket(portNum);
		Socket socket = serverSocket.accept();

		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		String echo;
		
		if(socket.isConnected()){
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			
			out.println(str);
			out.flush();
		}

		/*while ((echo = in.readLine()) != null) {
			
			if(echo.equals("start")){
				long time = System.currentTimeMillis(); 
				SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				String str = dayTime.format(new Date(time));
				
				out.println(str);
				out.flush();
			}else{
				System.out.println("echo: " + echo);
				out.println(echo);
				out.flush();
			}
			
		}*/

		in.close();
		out.close();
		socket.close();
		serverSocket.close();
	}
}
