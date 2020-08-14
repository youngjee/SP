package thread.multi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReaderThread implements Runnable {
	
	private MultiThreadExample mt = new MultiThreadExample();
	
	public FileReaderThread(MultiThreadExample mt) {
		this.mt = mt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File dir = new File("./INPUT");
		for(File file:dir.listFiles()) {
			if(!file.isDirectory())
				readFile(file);
		}
	}

	public void readFile(File file) {
		FileReader fileReader = null;

		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				mt.getStrList().add(line);
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

}
