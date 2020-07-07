package file.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//File모두읽기(비추)
public class FileReadAll {

	public static void main(String[] args) throws IOException {
		String readAll = read("./OUTPUT/INSP_006_20171123100000.TXT");
		System.out.println(readAll);
	}
	public static String read(String filePath) {

		StringBuilder stringBuilder;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			stringBuilder = new StringBuilder();
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null)
				stringBuilder.append(line).append('\n');

		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}finally {
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
		}

		return stringBuilder.toString();
	}

}
