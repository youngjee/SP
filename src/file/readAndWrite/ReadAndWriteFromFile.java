package file.readAndWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//파일에서 한줄씩 읽어서 한줄씩 쓰기
public class ReadAndWriteFromFile {
	
	public static void main(String[] args) throws IOException {
		readAndWriteFile("./INPUT/MyAll.txt", "./OUTPUT/MyAll.txt");
	}
	
	public static void readAndWriteFile(String readFileName, String writeFileName) throws IOException {
		FileReader fr = new FileReader(readFileName);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String temp = "";
		
		FileWriter fw = new FileWriter(writeFileName, true);// append true
		PrintWriter pw = new PrintWriter(fw);// flush false
		//BufferedWriter bw = new BufferedWriter(fw);

		while ((temp = br.readLine()) != null) {
			pw.println(temp);
			pw.flush();
			/*bw.append(temp);
			bw.newLine();
			bw.flush();*/
		}
		
		pw.close();
		//bw.close();
		fw.close();

		br.close();
		fr.close();

	}	
}
