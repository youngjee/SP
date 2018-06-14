package pattern;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//패턴 찾기
/*
 * 1) 숫자만 : ^[0-9]*$
2) 영문자만 : ^[a-zA-Z]*$
3) 한글만 : ^[가-힣]*$
4) 영어 & 숫자만 : ^[a-zA-Z0-9]*$
5) E-Mail : ^[a-zA-Z0-9]+@[a-zA-Z0-9]+$
6) 휴대폰 : ^01(?:0|1|[6-9]) - (?:\d{3}|\d{4}) - \d{4}$
7) 일반전화 : ^\d{2.3} - \d{3,4} - \d{4}$
8) 주민등록번호 : \d{6} \- [1-4]\d{6}
9) IP 주소 : ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3})

\w : 알파벳이나 숫자
\W : 알파벳이나 숫자 제외한 문자
\d : 숫자
\D : 숫자를 제외한 모든 문자

 */
public class MatcherExample {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
	}

	// 숫자패턴
	public boolean acceptNumber(String input) {
		// 숫자만 허용
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(input);

		return m.matches();
	}

	// 확장자 패턴
	public boolean acceptExtendsFile(String pathname) {
		String allowPattern = ".+\\.(TXT|xls|hwp|jpg|zip|tif|alz|bmp|pdf)$";
		boolean result = false;

		Pattern p = Pattern.compile(allowPattern);
		Matcher m = p.matcher(pathname);
		result = m.matches();

		return result;
	}
}
