package exam.fileWithSocket.SP18_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

	public static void main(String[] args) {
		
		FileManager fm = new FileManager();
		fm.setKey("LGCNS");
		System.out.println(fm.transLine("GWERRGJGEIVLKSJDTTTHHHHHHHHHHHHHHHHHPPP", "LGCNS"));
	}
	private File targetFile;
	private String key;
	
	public void searchFile(String fileName, File dir) {
		File[] fileList = dir.listFiles();
		for(File file:fileList) {
			if(file.isFile()) {
				if(file.getName().equals(fileName)) {
					targetFile = file;
					return;
				}
			}else {
				searchFile(fileName, file);
			}
		}
	}
	
	public List<String> fileArchive(String fileName, String key) {
		this.key = key;
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		searchFile(fileName, new File("BIGFILE"));

		List<String> contentList = new ArrayList<String>();
		try {
			fileReader = new FileReader(targetFile);
			bufferedReader = new BufferedReader(fileReader);
			String line;

			String beforeLine = "";
			int beforeLineCnt = 0;

			boolean isFirst = true;

			while ((line = bufferedReader.readLine()) != null) {

				if (isFirst) {
					beforeLine = line;
					beforeLineCnt = 1;
					isFirst = false;
				}else {
					// [[line 값 처리]]
					// System.out.println(line);
					if (beforeLine.equals(line)) {
						beforeLineCnt++;
					} else {
						if (beforeLineCnt != 1) {
							contentList.add(beforeLineCnt + "#" + transLine(beforeLine, key));
						} else {
							contentList.add(transLine(beforeLine, key));
						}

						beforeLine = line;
						beforeLineCnt = 1;
					}
				}
				
			}
			if (beforeLineCnt != 1) {
				contentList.add(beforeLineCnt + "#" + transLine(beforeLine, key));
			} else {
				contentList.add(transLine(beforeLine, key));
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

		return contentList;
	}
	
	public String standardWithKey(String key) {
		StringBuffer answer = new StringBuffer(key);
		
		String standard = "";
		for (char c = 'A'; c<='Z'; c++) {
			standard+=c;
		}
		for (int i = 0; i < standard.length(); i++) {
			char c = standard.charAt(i);
			if(!key.contains(String.valueOf(c))) {
				answer.append(c);
			}
		}
		
		return answer.toString();
	}

	public String transLine(String line, String key) {
		StringBuffer answer = new StringBuffer();
		char before = line.charAt(0);
		int beforeCnt = 1;

		for (int i = 1; i < line.length(); i++) {
			if (Character.isUpperCase(line.charAt(i))) {
				if (before == line.charAt(i)) {
					beforeCnt++;
				} else {
					if (beforeCnt >= 3) {
						answer.append(String.valueOf(beforeCnt)+String.valueOf(before));
					} else if (beforeCnt >1 && beforeCnt < 3) {
						answer.append(String.valueOf(before).repeat(beforeCnt));
					} else {
						answer.append(before);
					}
					before = line.charAt(i);
					beforeCnt = 1;
				}
			}
		}
		if (beforeCnt >= 3) {
			answer.append(String.valueOf(beforeCnt)+String.valueOf(before));
		} else if (beforeCnt >= 1 && beforeCnt < 3) {
			answer.append(String.valueOf(before).repeat(beforeCnt));
		} else {
			answer.append(before);
		}

		
		return moveLine(answer.toString());
	}
	
	public char encryptChar(char ch) {
		String standard = standardWithKey(key);
		return standard.charAt((int)(ch-'A'));
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String moveLine(String line) {
		StringBuffer sb = new StringBuffer();
		
		
		
		for (int i = 0; i < line.length(); i++) {
			if(Character.isUpperCase(line.charAt(i))) {
				sb.append(encryptChar(line.charAt(i)));
			}else {
				sb.append(line.charAt(i));
			}
		}
		
		return sb.toString();
	}

	public void archiveWrite(String filePath, String contents) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(contents);

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
