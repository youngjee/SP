package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static void main(String[] args) {
		System.out.println(getTime("20200120111111"));
	}
	
	public static Date getTime(String time) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = null;
		try {
			date = transFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// 현재시간 가져오기
	public static String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
		return dayTime.format(new Date(time));
	}

	public static long hourDiff(String strTime2, String strTime1) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Date date1 = transFormat.parse(strTime1);
		Date date2 = transFormat.parse(strTime2);

		//gap = hour
		long gap = date2.getTime() - date1.getTime();

		//1시간을 60분, 60초, 1000밀리세컨으로 나눔
		//결과는 millisecond
		//시간은 gap / 60 / 60 / 1000
		//분은 gap / 60 / 1000
		//초는 gap /1000
		//밀리세컨은 gap
		return gap / 60 / 60 / 1000;
	}

}
