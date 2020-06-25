package file.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//한 라인씩 읽기
public class FileReadLine {

	public static void main(String[] args) throws IOException {
		FileReader     fileReader     = null;
		BufferedReader bufferedReader = null;
		try {
		    fileReader     = new FileReader("./OUTPUT/INSP_006_20171123100000.TXT");
		    bufferedReader = new BufferedReader(fileReader);
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        //[[line 값 처리]]
		    	System.out.println(line);
		    }
		
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
		    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
		    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
		}

	}
}
