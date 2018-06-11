package socket.fileio.serverToClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server에서 Client로 여러개의 파일을 보내는 예제
 *
 */
public class FileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket s = new Socket("127.0.0.1", 30000);
		
		InputStream input = s.getInputStream();
		DataInputStream dInput = new DataInputStream(input);
		int fileListSize = dInput.readInt();
		
		File dir = new File("./INPUT");
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		for (int i = 0; i < fileListSize; i++){
			
			byte[] buffer = new byte[1024];
			
			int size = (int)dInput.readLong();
			String fName = dInput.readUTF();
			
			FileOutputStream fOut = new FileOutputStream("./INPUT/"+fName);
			while(size>0) {
				int data = input.read(buffer, 0, Math.min(size, buffer.length));
				size -= data;
				fOut.write(buffer, 0, data);
			}
			
			fOut.flush();
			fOut.close();
		}
		
		s.close();
		
		/*OutputStream output = s.getOutputStream();
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
		s.close();*/

	}
}
