package file.match;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//숫자를 제외한 나머지 문자열을 출력-> AFTER폴더에 write
public class FileReadMatcher {
	public static void main(String[] args) throws IOException {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileReader = new FileReader("./INPUT/INSP_006_20171123100000.TXT");
			bufferedReader = new BufferedReader(fileReader);
			String line;
			String after;
			
			StringBuffer sb = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				//알파벳이나 한글을 제외한 나머지 문자 삭제
				after = line.replaceAll("[^a-z^A-Z^가-힣]", "");
				//알파벳이나 한글 제외
				//after = line.replaceAll("[a-zA-Z가-힣]", "");
				
				//숫자 제외
				//after = line.replaceAll("\\d", "");
				
				//숫자 외의 나머지 문자 제외
				//after = line.replaceAll("\\D", "");
				System.out.println("바뀌기전:"+line);
				System.out.println("바뀐뒤:"+after);
				sb.append(after);
			}
			File dir = new File("./AFTER");
			if(!dir.exists()){
				dir.mkdirs();
			}
			fileWriter = new FileWriter("./AFTER/INSP_006_20171123100000_WITHOUT_NUM.TXT");
			bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(sb.toString());
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (Exception ex) { /* Do Nothing */
				}
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (Exception ex) { /* Do Nothing */
				}
			
			if (bufferedWriter != null)
				try {
					bufferedWriter.close();
				} catch (Exception ex) { /* Do Nothing */
				}
			
			if (fileWriter != null)
				try {
					fileWriter.close();
				} catch (Exception ex) { /* Do Nothing */
				}
		}

	}
}
