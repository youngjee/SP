package util;

public class StringUtil {
	
	public static void main(String[] args) {
		int[] seperator = {8, 7, 1, 14};
		PrintUtils.print(lineSubString("CARD_001BUS_001N20171019143610", seperator));
		
		String[] pattern = {"_", ".", "#"};
		PrintUtils.print(StringUtil.split("CARD_1111#3333.TXT", pattern));
		
		System.out.println("숫자인지 판단");
	}
	
	//line을 길이만큼 잘라서 배열로 리턴하는 함수
	public static String[] lineSubString(String line, int[] seperator) {
		String[] strArr = new String[seperator.length];
		int beginIdx = 0;
		for (int i = 0; i < seperator.length; i++) {
			strArr[i] = line.substring(beginIdx, beginIdx+seperator[i]);
			beginIdx = beginIdx+seperator[i];
		}
		return strArr;
	}
	
	//a에서 diff만큼 갔을때(알파벳나열에서) 해당 알파벳
	public static char getChar(char a, int diff) {
		//대문자일때
		int d = getIntForChar(a)+diff;
		if(d<0) {
			return (char)('Z'+d);
		}else {
			return getCharForInt(d);
		}
	}
	
	//str문자열에서 point위치에 해당하는 알파벳을 diff만큼 갔을 때 해당 알파벳(알파벳이 중복될 수 있으므로 알파벳이 아니라 위치로 파라미터 전달)
	public static char getChar(String str, int point, int diff) {
		//대문자일때
		int d = point+diff;
		if(d<0) {
			return str.charAt(str.length()+d);
		}else if(d>=str.length()) {
			return str.charAt(d-str.length());
		}else {
			return str.charAt(d);
		}
	}
	
	//a알파벳의 위치리턴('A'를 0으로 가정했을 때)
	public static int getIntForChar(char a) {
		return a-'A';
	}
	
	//숫자a의 문자리턴('A'를 0으로 가정했을 때)
	public static char getCharForInt(int a) {
		return (char)('A'+a);
	}
	
	
	public int diffChar(char a, char b) {
		return Math.min(Math.abs(a-b), Math.abs('Z'+(a-64)-b));
	}
	
	public static boolean isNumeric(String string) {

		if (string.equals("")) {
			// 문자열이 공백인지 확인
			return false;
		}
		return string.matches("-?\\d+(\\.\\d+)?");
	}
	
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isConsistWithNumbers(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
		
	public static boolean isConsistWithAlpha(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isAlphabetic(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	//abc-name => abcName
	public static String camelCaseToFieldName(String fieldName) {
		StringBuffer sb = new StringBuffer(fieldName);
		while (sb.indexOf("-") != -1) {
			int dashInx = sb.indexOf("-");
			sb.replace(dashInx, dashInx + 2, String.valueOf(Character.toUpperCase(sb.charAt(dashInx + 1))));
		}
		sb.setCharAt(0,Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	


	public static String[] split(String str, String[] patterns) {
		String pattern = "[";
		for (int i = 0; i < patterns.length; i++) {
			pattern = pattern + patterns[i];
			if(i != patterns.length-1) {
				pattern = pattern + "|";
						
			}
		}
		
		pattern = pattern+"]";
		System.out.println(pattern);
		
		return str.split(pattern);
	}
	
	
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
		}catch(Exception ex) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		}catch(Exception ex) {
			return false;
		}
		
		return true;
	}
}
