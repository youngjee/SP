package util;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class TimeUtil {
	//날짜정리
	//https://jekalmin.tistory.com/entry/%EC%9E%90%EB%B0%94-18-%EB%82%A0%EC%A7%9C-%EC%A0%95%EB%A6%AC
	
	public static void main(String[] args) {
		
		System.out.println(getCurrentTime());
		
		//날짜 가져오기
		LocalDate.now(); // 오늘 
		LocalDateTime.now(); // 지금 
		LocalDate.of(2015, 4, 17); // 2015년4월17일
		LocalDateTime.of(2015, 4, 17, 23, 23, 50); // 2015년4월17일23시23분50초 
		Year.of(2015).atMonth(3).atDay(4).atTime(10, 30); // 2015년3월4일 10시30분00초
		
		//기간 가져오기
		Period.ofYears(2); // 2년간(P2Y) 
		Period.ofMonths(5); // 5개월간(P5M) 
		Period.ofWeeks(3); // 3주간(P21D) 
		Period.ofDays(20); // 20일간(P20D) 
		Duration.ofDays(2); // 48시간(PT48H) 
		Duration.ofHours(8); // 8시간(PT8H) 
		Duration.ofMinutes(10); // 10분간(PT10M) 
		Duration.ofSeconds(30); // 30초간(PT30S)

		//날짜 + 기간 = 날짜
		LocalTime.of(9, 0, 0).plus(Duration.ofMinutes(10)); // (9시 + 10분간) = 9시10분 
		LocalDate.of(2015, 5, 15).plus(Period.ofDays(1)); // (2015년5월15일 + 1일간) = 2015년5월16일 
		LocalDateTime.of(2015, 4, 17, 23, 47, 5).minus(Period.ofWeeks(3)); // (2015년4월17일 23시47분05초 - 3주간) = 2015년3월27일 23시47분05초 
		LocalDate.now().plusDays(1); // (오늘 + 1일) = 내일 
		LocalTime.now().minusHours(3); // (지금 - 3시간) = 3시간 전

		//날짜 - 날짜 = 기간
		Period.between(LocalDate.of(1950, 6, 25), LocalDate.of(1953, 7, 27)); // (1953년7월27일 - 1950년6월25일) = 3년1개월2일간(P3Y1M2D) 
		Period.between(LocalDate.of(1950, 6, 25), LocalDate.of(1953, 7, 27)).getDays(); // 3년1개월2일간 => 2일간 
		LocalDate.of(1950, 6, 25).until(LocalDate.of(1953, 7, 27), ChronoUnit.DAYS); // 3년1개월2일간 => 1128일간 
		ChronoUnit.DAYS.between(LocalDate.of(1950, 6, 25), LocalDate.of(1953, 7, 27)); // 3년1개월2일간 => 1128일간 
		Duration.between(LocalTime.of(10, 50), LocalTime.of(19, 0)); // (19시00분00초 - 10시50분00초) = 8시간10분간(PT8H10M) 
		Duration.between(LocalDateTime.of(2015, 1, 1, 0, 0), LocalDateTime.of(2016, 1, 1, 0, 0)).toDays(); // 365일간 
		ChronoUnit.YEARS.between(LocalDate.of(2015, 5, 5), LocalDate.of(2017, 2, 1)); // 1년간

		//날짜 변환하기
		//LocalDate -> String
		LocalDate.of(2020, 12, 12).format(DateTimeFormatter.BASIC_ISO_DATE); // 20201212
		
		LocalTime.of(12, 20, 3).format(DateTimeFormatter.ofPattern("HH:mm:ss")); // 2015-04-18 00:42:24

		
		//LocalDateTime -> String
		LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 2015-04-18 00:42:24

		//LocalDateTime -> java.util.Date
		Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()); // Sat Apr 18 01:00:30 KST 2015

		// 요일로 날짜 가져오기
		LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)); // 다음 토요일
		LocalDate.of(2016, 5, 1).with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.SUNDAY)); // 2016년5월 세번째 일요일
		LocalDate.of(2015, 7, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015년7월 첫번째 월요일

	}
	
	//시간 구하기(String to LocalDateTime)
	public static LocalDateTime getTime(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
		
		return localDateTime;
	}
	
	// 현재시간 가져오기
	public static String getCurrentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
		return currentTime.format(formatter);
	}

	//시간 차이 구하기
	public static long hourDiff(String strTime1, String strTime2) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmss");
		LocalTime time1 = LocalTime.parse(strTime1, formatter);
		LocalTime time2 = LocalTime.parse(strTime2,formatter);
		Duration duration = Duration.between(time1, time2);
		
		return duration.toMinutes();
	}

}
