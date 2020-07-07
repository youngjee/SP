package file.util;

import java.io.File;

public class FileUtil {
	
	public static String getFileName(String filePath) {
		if(filePath.contains("/")) {
			return filePath.substring(filePath.lastIndexOf("/")+1);
		}else {
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
	 * 
	 * 
	 * @param strDirectoryPath
	 * 
	 *                         생성할 디렉토리명
	 * 
	 * @return 생성 성공 : true, 실패 : false
	 * 
	 */

	public static boolean createDirectory(String strDirectoryPath) {
		boolean retValue = false;
		retValue = new File(strDirectoryPath).mkdir();
		return retValue;
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
	 * @param sFileName 삭제 파일명 또는 디렉토리
	 * @return
	 */
	public static boolean delete(String strFileName)
	{
		File objDeleteFile = new File(strFileName);
		return objDeleteFile.delete();
	}

	/**
	 * 파일명 또는 디렉토리명 변경하기
	 * @param strSrc  기존 파일명 또는 디렉토리명
	 * @param strDest 변경될 파일명 또는 디렉토리명
	 * @return 변경 성공 여부
	 */
	public static boolean rename(String strSrc, String strDest)
	{
		boolean retValue = false;

		File objSrcFile = new File(toEncoding(strSrc)); // 파일 또는 디렉토리가 한글 일 경우
		File objDestFile = new File(toEncoding(strDest)); // 파일 또는 디렉토리가 한글 일 경우

		try{
			retValue = objSrcFile.renameTo(objDestFile);
		}catch (Exception e){
			e.printStackTrace();
		}
		return retValue;
	}
}
