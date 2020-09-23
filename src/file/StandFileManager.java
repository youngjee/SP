package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class StandFileManager {
	
	private File targetFile = null;
	
	public void run(String[] args) {
		//input
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNext()) {
			String fileName = scanner.nextLine();
			searchFile(fileName, new File("./BIGFILE"));
						
			String result = readFile(targetFile);
			write("./"+fileName, result);
		}
	}
	
	public File searchFile(String filename) {
		File file = new File("./BIGFILE/"+filename);
		if(file.exists()) {
			return file;
		}else {
			throw new RuntimeException("no file");
		}
	}
	
	public void searchFile(String fileName, File dir) {
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			if (file.isFile()) {
				if (file.getName().equals(fileName)) {
					targetFile = file;
					return;
				}
			} else {
				searchFile(fileName, file);
			}
		}
	}
	
	public String readFile(File file) {
		FileReader     fileReader     = null;
		BufferedReader bufferedReader = null;
		
		StringBuffer sb = new StringBuffer();
		
		try {
		    fileReader     = new FileReader(file);
		    bufferedReader = new BufferedReader(fileReader);
		    String line = null;
		    
		    while ((line = bufferedReader.readLine()) != null) {
		        //[[line 값 처리]]
		    	String transLine = processLine(line);
		    	System.out.println(line+"->"+transLine);
		    }
		    
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
		    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
		    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
		}

		return sb.toString();
	}
	
	public String processLine(String line) {
		StringBuffer sb = new StringBuffer();
	
		return sb.toString();
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
