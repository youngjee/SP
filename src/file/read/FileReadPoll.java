package file.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileReadPoll {

	    public static void main(String[] args) throws Exception {
	        Scanner scan = new Scanner(System.in);
	        /*Just To add functionality I have also specified the time at which the file is modified*/
	        System.out.println("Input File Path : ");
	        String file_path = scan.nextLine();
	        RandomAccessFile f = new RandomAccessFile(file_path,"r");
	        int character;
	        String Intitalval="";
	        while ((character = f.read()) != -1) {
	            Intitalval+=(char)character;
	        }
	        f.seek(0);
	        boolean nochange = true;
	        while (nochange) {
	            String ToCompare="";
	            while ((character = f.read()) != -1) {
	                ToCompare+=(char)character;
	            }

	            if (!ToCompare.equals(Intitalval)) {
	                //nochange = false;
	                System.out.println("The file has been modified at " + java.time.LocalTime.now());
	            }
	            Thread.sleep(1);
	            f.seek(0);
	        }
	        f.close();
	    }
	    
	    public void read(){
			FileReader     fileReader     = null;
			BufferedReader bufferedReader = null;
			try {
			    fileReader     = new FileReader("./aaa.TXT");
			    bufferedReader = new BufferedReader(fileReader);
			    String line;
			    while (true) {
			    	Thread.sleep(1000);
			    	if((line = bufferedReader.readLine()) != null) {
			    		if(line.equals("end")) {
			    			break;
			    		}
			    		//[[line 값 처리]]
				    	System.out.println(line);
			    	}
			        
			    }
			
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			} finally {
			    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
			    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
			}

		}
}
