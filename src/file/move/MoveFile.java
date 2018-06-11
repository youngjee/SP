package file.move;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//OUTPUT파일을 MOVE폴더로 이동
public class MoveFile {
	public static void main(String[] args) {
		File dir = new File("./OUTPUT");
		if (dir.exists()) {
			File[] files = dir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					// 필요경우 filter쓰기
					//확장자 제한하기
					/*String allowPattern = ".+\\.(TXT|xls|hwp|jpg|zip|tif|alz|bmp|pdf)$";
					boolean result = false;

					Pattern p = Pattern.compile(allowPattern);
					Matcher m = p.matcher(pathname.getName());
					result = m.matches();

					return result;*/
					return true;
				}
			});

			File moveDir = new File("./MOVE");
			if (!moveDir.exists()) {
				moveDir.mkdirs();
			}

			for (File file : files) {
				//File이름도 바꾸기
				/*String fileName = file.getName();
				int inx = fileName.lastIndexOf(".");
				String realFName = fileName.substring(0, inx);
				String ext = fileName.substring(inx+1);
				file.renameTo(new File("./MOVE/" + realFName + "_moved"+"."+ext));*/
				file.renameTo(new File("./MOVE/" + file.getName()));
			}

			// 원래 directory는 지우기
			dir.deleteOnExit();
		}
	}
}
