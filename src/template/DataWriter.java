package template;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class DataWriter {

	public void write(String filePath, List<String> dataList) {

		//data를 string으로 합체
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < dataList.size(); i++) {
			sb.append(dataList.get(i)).append("\n");
		}
		
		//쓰기
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(sb.toString());

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
