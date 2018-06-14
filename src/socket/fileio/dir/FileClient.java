package socket.fileio.dir;

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
	
	private Socket s;
	private OutputStream output;
	private DataOutputStream dOut;
	public String rootPath;
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		FileClient client = new FileClient();
		client.connect();
		
		File rootFile = new File("./INPUT");
		client.rootPath = rootFile.getCanonicalPath();
		client.sendData(rootFile);
		client.close();
	}
	
	public void connect() throws UnknownHostException, IOException {
		s = new Socket("127.0.0.1", 30000);
		output = s.getOutputStream();
		dOut = new DataOutputStream(output);
	}
	
	public void sendData(File dir) throws IOException{
		File[] files = dir.listFiles();
		//dOut.writeInt(files.length);

		for (File file : files) {
			if(file.isDirectory()){
				sendData(file);
			}else{
				FileInputStream fInput = new FileInputStream(file);

				dOut.writeLong( file.length());
				//dOut.writeUTF(file.getName());
				System.out.println(file.getCanonicalPath().replace(rootPath, ""));
				dOut.writeUTF(file.getCanonicalPath().replace(rootPath, ""));

				int SIZE = 1024;
				byte[] buffer = new byte[SIZE];

				int readLine;
				while ((readLine = fInput.read(buffer)) != -1) {
					output.write(buffer, 0, readLine);
				}

				fInput.close();
			}
		}
	}
	
	public static void subDirList(String source) {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					// 파일이 있다면 파일 이름 출력
					System.out.println(file.getAbsolutePath());
				} else if (file.isDirectory()) {
					System.out.println(file.getAbsolutePath());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString());
				}
			}
		} catch (IOException e) {

		}
	}
	
	public void close() throws IOException{
		dOut.close();
		output.close();
		s.close();
	}
}
