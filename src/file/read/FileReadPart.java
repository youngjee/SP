package file.read;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReadPart {
	
	public static void main(String[] args) throws IOException {
		byte[] data = readBytes("./INPUT/MyAll.txt", 0, 100);
		String str = new String(data);
		System.out.println(str);
		
		
	}

	public static byte[] readBytes(String filePath, int offset, int length)
			throws IOException {

		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(filePath, "r");
			randomFile.seek(offset);

			byte[] dataBytes = new byte[length];
			randomFile.readFully(dataBytes);

			return dataBytes;

		}catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (randomFile != null)
				try {
					randomFile.close();
				} catch (Exception ex) { /* Do Nothing */
				}
		}
	}
}
