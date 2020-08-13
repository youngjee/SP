package socket.multi.notPool;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class MultiServerWithoutPool {
	public final static int PORT = 13;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					Thread task = new DaytimeThread(connection);
					task.start();

				} catch (IOException e) {
				}
			}

		} catch (IOException e) {
			System.err.println("스타트 서버에 연결할 수 없습니다.");

		}

	}

	private static class DaytimeThread extends Thread {

		private Socket connection;

		DaytimeThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				out.write(LocalDate.now().toString() + "\r\n");
				out.flush();
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
