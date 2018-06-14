package file.subDir;

import java.io.File;
import java.io.IOException;

public class SubDirSearch {
	
	public static void main(String[] args) {
		subDirList("./INPUT");
	}
	
	public static void subDirList(String source) {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					// 파일이 있다면 파일 이름 출력
					System.out.println(file.getAbsolutePath());
				} else if (file.isDirectory()) {
					System.out.println(file.getAbsolutePath());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString());
				}
			}
		} catch (IOException e) {

		}
	}

}
