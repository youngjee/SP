package thread.multi;

import java.util.ArrayList;
import java.util.List;


public class MultiThreadExample{
	
	private List<String> strList = new ArrayList<String>();
	
	public static void main(String[] args) {
		MultiThreadExample mt = new MultiThreadExample();
		FileReaderThread fileThread = new FileReaderThread(mt);
		ScannerThread scannerThread = new ScannerThread(mt);
		
		Thread ft = new Thread(fileThread);
		Thread st = new Thread(scannerThread);
		
		try {
			ft.start();
			st.start();
			
			ft.join();
			st.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mt);
		System.out.println("Thread finish");
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return strList.toString();
	}
	
	
	
}
