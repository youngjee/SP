package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BigFileManager {
	
	private File targetFile = null;
	private List<String> origin = new ArrayList<String>();
	
	public void run(String[] args) {
		//input
		Scanner scanner = new Scanner(System.in);
		
		if(scanner.hasNext()) {
			String fileName = scanner.nextLine();
			searchFile(fileName, new File("./BIGFILE"));
			
			makeChanged();
			
			String result = readFile(targetFile);
			write("./"+fileName, result);
		}
		//search file
		//zip
		//output
		//finish
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
		    String preLine = null;
		    
		    int equalCnt = 0;
		    
		    while ((line = bufferedReader.readLine()) != null) {
		        //[[line 값 처리]]
		    	System.out.println(line);
		    	if(preLine == null) {
		    		preLine = line;
		    		equalCnt = 1;
		    	}else {
		    		if(preLine.equals(line)) {
		    			equalCnt++;
		    		}else {
		    			if(equalCnt==1) {
		    				sb.append(transLine(preLine));
		    				sb.append(System.lineSeparator());
		    			}else {
		    				sb.append(equalCnt+"#"+transLine(preLine));
		    				sb.append(System.lineSeparator());
		    			}
		    			preLine = line;
		    			equalCnt = 1;
		    		}
		    	}
		    	
		    }
		    
			if(equalCnt==1) {
				sb.append(transLine(preLine));
				sb.append(System.lineSeparator());
			}else {
				sb.append(equalCnt+"#"+transLine(preLine));
				sb.append(System.lineSeparator());
			}
    			
		    
		
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
		    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
		    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
		}

		return sb.toString();
	}
	
	//AABCDEEEFFGHHHHIIIJK
	//AABCDEEEFFGHHHHIIIJK -> AABCD3EFFG4H3IJK
	public String transLine(String line) {
		StringBuffer sb = new StringBuffer();
		
		String[] chars = line.split("");
		String current = null;
		String pre = null;
		int equalCnt = 0;
		
		for (int i = 0; i < chars.length; i++) {
			current = chars[i];
			if(pre == null) {
				pre = current;
				equalCnt = 1;
			}else {
				if(pre.equals(current)) {
					equalCnt ++;
				}else {
					if(equalCnt<=2) {
	    				sb.append(pre.repeat(equalCnt));
	    			}else {
	    				sb.append(equalCnt+pre);
	    			}
					pre = current;		
					equalCnt = 1;
				}
			}
		}
		
		if(equalCnt<=2) {
			sb.append(pre.repeat(equalCnt));
		}else {
			sb.append(equalCnt+pre);
		}
		
		return sb.toString();
	}
//	public String transLine(String line) {
//		StringBuffer sb = new StringBuffer();
//		
//		String[] chars = line.split("");
//		String current = null;
//		String pre = null;
//		int equalCnt = 0;
//		
//		for (int i = 0; i < chars.length; i++) {
//			current = chars[i];
//			if(pre == null) {
//				pre = current;
//				equalCnt = 1;
//			}else {
//				if(pre.equals(current)) {
//					equalCnt ++;
//				}else {
//					if(equalCnt<=2) {
//	    				sb.append(transOne(pre).repeat(equalCnt));
//	    			}else {
//	    				sb.append(equalCnt+transOne(pre));
//	    			}
//					pre = current;		
//					equalCnt = 1;
//				}
//			}
//		}
//		
//		if(equalCnt<=2) {
//			sb.append(transOne(pre).repeat(equalCnt));
//		}else {
//			sb.append(equalCnt+transOne(pre));
//		}
//		
//		return sb.toString();
//	}
	
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

	

	
	public String transOne(String one) {
		
		if(one.matches("[A-Z]")) {
			int num = one.charAt(0)-'A';
			return origin.get(num);
		}else {
			return one;
		}
		
	}
	

	
	private void makeChanged() {
		for (char i = 'A'; i <='Z'; i++) {
			origin.add(String.valueOf(i));
		}
		String cns = "LGCNS";
		String[] cnsArr = cns.split("");
		for (int i = 0; i < cnsArr.length; i++) {
			origin.remove(cnsArr[i]);
		}
		origin.addAll(0, Arrays.asList(cnsArr));
		
	}
}
