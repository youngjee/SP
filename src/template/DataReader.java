package template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

	List<String> lineList = new ArrayList<String>();
	
	
	public List<String> read(String value, String filePath) {
		
		List<String> dataList = new ArrayList<String>();
		
		//StringBuilder stringBuilder;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			//stringBuilder = new StringBuilder();
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null)
				dataList.add(line);
				//stringBuilder.append(line).append('\n');

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
		
		return dataList;

	}
	
}
