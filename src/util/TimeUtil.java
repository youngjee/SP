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

		long gap = date2.getTime() - date1.getTime();

		return gap / 60 / 60 / 1000;
	}

}
