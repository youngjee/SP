package file.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//파일쓰기
public class FileWrite {

	public static void main(String[] args) throws IOException {
		write("./OUTPUT/newfile.txt", "aaaaaaa");
	}

	public static void write(String filePath, String content) {

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

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
