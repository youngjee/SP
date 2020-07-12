package util;

public class StringUtil {

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

}
