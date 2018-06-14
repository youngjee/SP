package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalculate {
	public static void main(String[] args) throws ParseException {
		DateCalculate dc = new DateCalculate();
		//timeout이면 true, 아니면 false
		System.out.println(dc.isTimeout());
	}
	
	//현재시간 가져오기
	public String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
		return dayTime.format(new Date(time));
	}
	
	public boolean isTimeout() throws ParseException {

		// 요청시간
		String reqDateStr = "201702011535";

		// 현재시간 Date
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
		Date reqDate = dateFormat.parse(reqDateStr);

		Calendar cal = Calendar.getInstance();
		// 1일이 경과했는지 확인
		// 일단위: cal.add(Calendar.DATE, 며칠*-1);
		// 시간단위: cal.add(Calendar.HOUR, 몇시간*-1);
		int date = 2; // 2일경과
		// 현재시간에서 2일 전 계산
		cal.add(Calendar.DATE, date * -1);
		Date comparedTime = cal.getTime();

		// 현재시간에서 2일전과 비교시간(reqDateStr) 비교하여 초과했으면 시간경과!
		if (comparedTime.compareTo(reqDate) > 0) {
			return true;
		}
		return false;
	}

	public void timeCalculator() throws ParseException {
		// 요청시간
		String reqDateStr = "201702011535";

		// 포맷정하기
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
		// 현재시간 Date
		Date curDate = new Date();

		// 요청시간을 Date로 parsing 후 time가져오기
		Date reqDate = dateFormat.parse(reqDateStr);
		long reqDateTime = reqDate.getTime();

		// 현재시간을 요청시간의 형태로 format 후 time 가져오기
		curDate = dateFormat.parse(dateFormat.format(curDate));
		long curDateTime = curDate.getTime();
		// 분으로 표현
		long minute = (curDateTime - reqDateTime) / 60000;
		System.out.println("요청시간 : " + reqDate);
		System.out.println("현재시간 : " + curDate);
		System.out.println(minute + "분 차이");

	}


}
