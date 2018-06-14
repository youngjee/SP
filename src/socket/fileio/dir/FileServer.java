package socket.fileio.dir;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Client에서 Server로 여러개의 파일을 보내는 예제
 * @author 74727
 *
 */
public class FileServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(30000);
		Socket listener = server.accept();
		
		InputStream input = listener.getInputStream();
		DataInputStream dInput = new DataInputStream(input);
		//int fileListSize = dInput.readInt();
		
		File dir = new File("./OUTPUT");
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		try{
			while(true){
				byte[] buffer = new byte[1024];
				
				int size = (int)dInput.readLong();
				String fName = dInput.readUTF();
				
				File file = new File("./OUTPUT"+fName.substring(0, fName.lastIndexOf(File.separator)));
				if(!file.exists()){
					file.mkdirs();
				}
				FileOutputStream fOut = new FileOutputStream("./OUTPUT/"+fName);
				while(size>0) {
					int data = input.read(buffer, 0, Math.min(size, buffer.length));
					size -= data;
					fOut.write(buffer, 0, data);
				}
				
				fOut.flush();
				fOut.close();
			}
		}catch(EOFException ex){
			System.out.println("다읽음");
		}finally{
			dInput.close();
			input.close();
			listener.close();
			server.close();
		}
		
	}
}
