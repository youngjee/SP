package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FolderUtil {

	public static void main(String[] args) {
		FolderUtil util = new FolderUtil();
		File dir = new File("./INPUT");
		util.searchFile("MyAll.txt", dir);
//		// folder안에 파일 모두 출력
//		subDirList("./INPUT");
//
//		// delete
//		String path = "D:\\Eclipse\\Java\\새 폴더";
//		deleteFolder(path);
//
//		// copy
//		File folder1 = new File("D:\\Eclipse\\Java\\복사할폴더\\복사될폴더");
//		File folder2 = new File("D:\\Eclipse\\Java\\복사된폴더");
//		copy(folder1, folder2);
		if(util.targetFile == null) {
			System.out.println("no file");
		}else {
			System.out.println("searched");
		}
	}

	File targetFile = null;

	public static File findFileSub(String name) {

		File file = new File("./BIGFILE");
		return findFileSub(file, name);
	}

	/**
	 * 파일찾기
	 * @param file
	 * @param name
	 * @return
	 */
	public static File findFileSub(File file, String name) {

		File[] files = file.listFiles();

		for (File localFile : files) {

			if (localFile.isDirectory()) {
				File subFile = findFileSub(file, name);
				if (subFile != null)
					return subFile;
			} else {
				if (name.equals(localFile.getName())) {
					return localFile;
				}
			}
		}

		return null;
	}

	/**
	 * 하위파일 모두 삭제
	 * @param path
	 */
	public static void deleteFolder(String path) {

		File folder = new File(path);
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

				for (int i = 0; i < folder_list.length; i++) {
					if (folder_list[i].isFile()) {
						folder_list[i].delete();
						System.out.println("파일이 삭제되었습니다.");
					} else {
						deleteFolder(folder_list[i].getPath()); // 재귀함수호출
						System.out.println("폴더가 삭제되었습니다.");
					}
					folder_list[i].delete();
				}
				folder.delete(); // 폴더 삭제
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * 파일복사
	 * @param sourceF
	 * @param targetF
	 */
	public static void copy(File sourceF, File targetF) {
		File[] target_file = sourceF.listFiles();
		for (File file : target_file) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if (file.isDirectory()) {
				temp.mkdir();
				copy(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					while ((cnt = fis.read(b)) != -1) {
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 하위에서 파일 찾기
	 * @param fileName
	 * @param dir
	 */
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

	/*
	 * 하위 파일리스트 출력
	 */
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
