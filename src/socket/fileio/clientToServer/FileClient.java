package socket.fileio.clientToServer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client에서 Server로 여러개의 파일을 보내는 예제
 * @author 74727
 *
 */
public class FileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket s = new Socket("127.0.0.1", 30000);
		OutputStream output = s.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(output);

		File dir = new File("./INPUT");
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
		s.close();

	}
}
