package exam.fileWithSocket.SP18_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunManager {

	/**
	 * socket client에서 File명을 받아서 server에서 해당파일을 찾고 
	 * 파일내용을 압축/암호화해서 요청에따라(ACK/ERROR/숫자) 한 라인씩 전달하는 코드
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(9876);
		Socket listener = server.accept();

		PrintWriter out = new PrintWriter(listener.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(listener.getInputStream()));

		char[] readStr = new char[100];
		int r = in.read(readStr);
		
		FileManager fm = new FileManager();
				
		String firstLine = String.valueOf(readStr).trim();
		String[] lineArr = firstLine.split("#");
		List<String> contentList = fm.fileArchive(lineArr[0], lineArr[1]);
		out.println(contentList.get(0));
		out.flush();
		
		int i = 0;
		while(i<contentList.size()) {
			readStr = new char[40];
			in.read(readStr);
			String line = String.valueOf(readStr).trim();
			
			if("ACK".equals(String.valueOf(readStr).trim())) {
				//다음전송
				i++;
				out.println(contentList.get(i));
			}else if("ERR".equals(line)) {
				//재전송
				out.println(contentList.get(i));
			}else if(isDigit(line)){
				out.println(contentList.get(Integer.parseInt(line)));
				i = Integer.parseInt(line);
			}
			out.flush();
			if(i==contentList.size()-1) {
				break;
			}
		}
		
	}
	
	public static boolean isDigit(String str) {
		Pattern p = Pattern.compile("^(\\d)+$");
		Matcher m = p.matcher(str);
		
		if(m.matches()) {
			return true;
		}else {
			return false;
		}
	}

}
