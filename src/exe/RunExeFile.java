package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//exe파일 실행
public class RunExeFile {
	public static void main(String[] args) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String exeFile = "CODECONV.EXE";
		Process p;
		//파라미터 없는경우
		//p = rt.exec(exeFile + " " + "실행할 파라미터");
		
		//파라미터 있는경우
		p = rt.exec(exeFile);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		
		//나온 결과값
		String value = br.readLine();
		System.out.println(value);
	}
}
