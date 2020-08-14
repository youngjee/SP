package file.divide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogManager {
	private List<Info> logOriginData = new ArrayList<Info>();
//	private List<Analyze> logAnalyzeData = new ArrayList<Analyze>();
	private Map<String, Integer> analyzeLog = new HashMap<String, Integer>();

	public void job() {
		readLogFile();
		writeOutputFile();
	}

	public void readLogFile() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader("./INPUT/LOG/log.txt");
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				translateLine(line);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (Exception ex) {
					/* Do Nothing */ }
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (Exception ex) {
					/* Do Nothing */ }
		}
	}
	
	public void writeOutputFile() {
		StringBuffer sb = new StringBuffer();
		List<String> keyList = new ArrayList<String>(analyzeLog.keySet());
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(String key:keyList) {
			sb.append(key+"#"+analyzeLog.get(key));
			sb.append(System.lineSeparator());
		}
		writeFile("./OUTPUT/LOG_1.TXT", sb.toString());
		
	}

	public void writeFile(String filePath, String content) {

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

	public void translateLine(String line) {
		String[] strs = line.split("#");
		logOriginData.add(new Info(strs[0], strs[1], strs[2]));
		analyzeLog.put(strs[1], analyzeLog.getOrDefault(strs[1], 0) + 1);
	}

}
