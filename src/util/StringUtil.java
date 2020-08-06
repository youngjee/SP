package util;

public class StringUtil {
	
	public static void main(String[] args) {
		int[] seperator = {8, 7, 1, 14};
		PrintUtils.print(lineSubString("CARD_001BUS_001N20171019143610", seperator));
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

}
