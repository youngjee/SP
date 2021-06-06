package file.read;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UpdateAndPrint {
	
	public static void main(String[] args) {
		new UpdateAndPrint().doSomethingAfterUpdate();
	}
	
	public void doSomething(String line) {
		System.out.println(line);
	}

	public void doSomethingAfterUpdate() {
		try {
		    File file = new File("./aaa.TXT");
		    while (file.length() <= 0) {
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		     
		    RandomAccessFile rFile = new RandomAccessFile(file, "r");
		    String existedline = "";
		    
		    while((existedline = rFile.readLine()) != null ) {
		    	System.out.println(existedline);
		    }
		    
		    rFile.seek(file.length());
		     
		    String line = null;
		    while (true) {
		        line = rFile.readLine();
		        if (line == null || line.isEmpty()) {
		            try {
		                Thread.sleep(500);
		                continue;
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		 
		        //로직 처리        
		        doSomething(line);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
