package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	public static void main(String[] args) throws FileNotFoundException {
		File dir = new File("./TEMP_DEST");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		FileUtil.copy(new File("./GUESTS"), dir);
	}

	public static String getFileName(String filePath) {
		if (filePath.contains("/")) {
			return filePath.substring(filePath.lastIndexOf("/") + 1);
		} else {
			return filePath;
		}
		
		
	}

	/**
	 * 
	 * 경로 가져오기(문자열 처리)
	 * 
	 * @param strFullPath 경로
	 * 
	 * @return
	 * 
	 */
	public static String getPath(String strFullPath) {
		if (strFullPath == null || strFullPath.length() < 1) {
			return strFullPath;
		}

		int nPosLast = strFullPath.lastIndexOf("/");

		// 구분자 '/' 없고, 확장자가 없을 경우
		if (nPosLast == -1 && strFullPath.indexOf(".") == -1)
			return strFullPath;

		return strFullPath.substring(0, nPosLast);
	}

	/**
	 * 
	 * 디렉토리 생성하기
	 *
	 * @param strDirectoryPath 생성할 디렉토리명
	 * @return 생성 성공 : true, 실패 : false
	 * 
	 */
	public static boolean createDirectory(String dirPath) {
		return new File(dirPath).mkdirs();
	}

	public static boolean createSubDirectory(String fileName) {
		String dirPath = fileName.substring(0, fileName.lastIndexOf("/"));

		return new File(dirPath).mkdirs();
	}

	/**
	 * 지정한 경로 값의 디렉토리 여부
	 *
	 * @param strFileName 경로
	 * @return 디렉토리일 경우 true, 아닐 경우 false
	 */
	public static boolean isDirectory(String strFileName) {
		boolean retValue = false;
		try {
			retValue = new File(strFileName).isDirectory();
		} catch (Exception e) {
			e.printStackTrace();
			retValue = false;
		}
		return retValue;
	}

	/**
	 * 파일 또는 디렉토리가 한글일 경우 인코딩 처리하기
	 * 
	 * @param sString 파일명 또는 디렉토리명
	 * @return 인코딩 처리된 파일명 또는 디렉토리명
	 */
	private static String toEncoding(String strString) {
		String retValue = null;
		try {
			retValue = new String(strString.getBytes("8859_1"), "KSC5601");
		} catch (Exception e) {
			e.printStackTrace();
			retValue = strString;
		}
		return retValue;
	}

	/**
	 * 파일 또는 디렉토리 존재 여부
	 * 
	 * @param strFileName 파일명 또는 디렉토리명
	 * @return 존재한다고면 true, 존재하지 않는다면 false
	 * 
	 */
	public static boolean isExists(String strFileName) {
		File objFile = new File(strFileName);
		return objFile.exists();
	}

	/**
	 * 파일 또는 디렉토리 삭제하기
	 *
	 * @param strFileName 삭제할 파일 또는 디렉토리
	 * @param bChild      하위 디렉토리 및 파일 삭제 여부
	 * @return
	 * 
	 */
	public static boolean delete(String strFileName, boolean bChild) {
		File objFile = new File(strFileName);
		try {
			if (!objFile.exists()) { // 파일 또는 디렉토리가 존재하지 않는다면
				return false;
			}

			if (objFile.delete()) {
				return true;
			}

			else if (objFile.isDirectory()) {
				if (!bChild) {
					return false;
				}

				if (!strFileName.substring(strFileName.length() - 1).equals("/")) {
					strFileName = strFileName + "/";
				}
				boolean bDelete = true;

				/***********************************************************************************
				 * 
				 * 하위 폴더는 존재하지 않는다는 가정하에 디렉토리를 삭제한다.
				 * 
				 **********************************************************************************/

				String[] arsFileList = objFile.list();
				for (int i = 0; i < arsFileList.length; i++) {
					System.out.println(strFileName + arsFileList[i]);
					if (!delete(strFileName + arsFileList[i])) {
						if (bDelete) {
							bDelete = false;
						}
					}
				}

				if (bDelete) {
					bDelete = objFile.delete();
				}
				return bDelete;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 파일 또는 디렉토리 삭제하기
	 * 
	 * @param sFileName 삭제 파일명 또는 디렉토리
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File objDeleteFile = new File(strFileName);
		return objDeleteFile.delete();
	}

	/**
	 * 파일명 또는 디렉토리명 변경하기
	 * 
	 * @param strSrc  기존 파일명 또는 디렉토리명
	 * @param strDest 변경될 파일명 또는 디렉토리명
	 * @return 변경 성공 여부
	 */
	public static boolean rename(String strSrc, String strDest) {
		boolean retValue = false;

		File objSrcFile = new File(toEncoding(strSrc)); // 파일 또는 디렉토리가 한글 일 경우
		File objDestFile = new File(toEncoding(strDest)); // 파일 또는 디렉토리가 한글 일 경우

		try {
			retValue = objSrcFile.renameTo(objDestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}
	
	//하위 파일(디렉토리포함) 모두 복사
	public static void copy(File sourceF, File targetF){
		File[] target_file = sourceF.listFiles();
		for (File file : target_file) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if(file.isDirectory()){
				temp.mkdir();
				copy(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp) ;
					byte[] b = new byte[4096];
					int cnt = 0;
					while((cnt=fis.read(b)) != -1){
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
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


	// 파일복사
	private static void copyFile(File source, File dest) {
		long startTime = System.currentTimeMillis();

		int count = 0;
		long totalSize = 0;
		byte[] b = new byte[128];

		FileInputStream in = null;
		FileOutputStream out = null;
		// 성능향상을 위한 버퍼 스트림 사용
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			in = new FileInputStream(source);
			bin = new BufferedInputStream(in);

			out = new FileOutputStream(dest);
			bout = new BufferedOutputStream(out);
			while ((count = bin.read(b)) != -1) {
				bout.write(b, 0, count);
				totalSize += count;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {// 스트림 close 필수
			try {
				if (bout != null) {
					bout.close();
				}
				if (out != null) {
					out.close();
				}
				if (bin != null) {
					bin.close();
				}
				if (in != null) {
					in.close();
				}

			} catch (IOException r) {
				// TODO: handle exception
				System.out.println("close 도중 에러 발생.");
			}
		}
		// 복사 시간 체크
		StringBuffer time = new StringBuffer("소요시간 : ");
		time.append(System.currentTimeMillis() - startTime);
		time.append(",FileSize : " + totalSize);
		System.out.println(time);
	}

	// 디렉토리 생성 -> 파일복사
	private static void copyDirectory(File source, File dest) {
		long startTime = System.currentTimeMillis();

		if (!source.exists() || !dest.isDirectory()) {
			throw new IllegalArgumentException("디렉토리 없음");
		}

		dest.mkdirs();// 생성

		File[] fileList = source.listFiles();// 내부의 파일리스트 가져오기

		for (int i = 0; i < fileList.length; i++) {
			File sourceFile = fileList[i];

			File destFile = new File(dest, sourceFile.getName());
			copyFile(sourceFile, destFile);// copyFile메소드 실행
		}

		// 복사 시간 체크
		StringBuffer time = new StringBuffer("소요시간 : ");
		time.append(System.currentTimeMillis() - startTime);
		time.append(",File Total List : " + fileList.length);
		System.out.println(time);
	}

}
