package socket.fileio.serverToClient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server에서 Client로 여러개의 파일을 보내는 예제
 *
 */
public class FileServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(30000);
		Socket listener = server.accept();
		
		OutputStream output = listener.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(output);

		File dir = new File("./OUTPUT");
		File[] files = dir.listFiles();
		dOut.writeInt(files.length);

		for (File file : files) {
			FileInputStream fInput = new FileInputStream(file);

			dOut.writeLong( file.length());
			dOut.writeUTF(file.getName());

			int SIZE = 1024;
			byte[] buffer = new byte[SIZE];

			int readLine;
			while ((readLine = fInput.read(buffer)) != -1) {
				output.write(buffer, 0, readLine);
			}

			fInput.close();
		}
		
		dOut.close();
		output.close();
		listener.close();
		
	}
}
