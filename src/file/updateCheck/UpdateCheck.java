package file.updateCheck;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UpdateCheck {
	
	public static void main(String[] args) {
		new UpdateCheck().doSomethingAfterUpdate();
	}
	
	public void doSomething() {
		System.out.println("do something");
	}

	public void doSomethingAfterUpdate() {
		try {
		    File file = new File("REMOTE.TXT");
		    while (file.length() <= 0) {
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		     
		    RandomAccessFile rFile = new RandomAccessFile(file, "r");
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
		        doSomething();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
