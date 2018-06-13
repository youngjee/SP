package file.readAndWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//콘솔에 값 입력하면서 파일에 쓰기
public class ReadAndWriteFromFile {
	
	public static void main(String[] args) 
    {
        String fname="aaa.txt";
        ReadAndWriteFromFile baw=new ReadAndWriteFromFile();
        try{
            baw.readnwrite(fname,false);//clear
            //baw.readnwrite("aaa.txt",true);//append
            baw.readFile(fname);
        }catch(Exception ex){
            System.out.println(ex);

        }
    }

	public void readFile(String fn) throws IOException {

		FileReader fr = new FileReader(fn);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String temp = "";

		while ((temp = br.readLine()) != null) {
			sb.append(temp + "\n");
		}

		System.out.println(sb.toString());
		br.close();
		fr.close();

	}

	public void readnwrite(String fn, boolean append) throws IOException {

		String s = null;
		FileWriter fw = new FileWriter(fn, append);// append true
		PrintWriter pw = new PrintWriter(fw);// flush false

		// PrintWriter pw=new PrintWriter(fw, true);//flush true

		while ((s = readbuff()) != null ) { // CTRL+C
			pw.println(s);
			pw.flush();// PrintWriter(fw, true)일 때는 필요없다.
		}
		pw.close();
		fw.close();
	}

	public String readbuff() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}

}
