package util;

public class CharUtil {
	
	public static void main(String[] args) {
		System.out.println(differentBetweenChars('A', 'Z'));
	}
	/**
	 * a와 b사이 최소차이('A'일때 반대로가면 'Z'가 된다고 가정하면..)
	 * @param a 작은 문자
	 * @param b 큰 문자
	 * @return
	 */
	public static int differentBetweenChars(char a, char b) {
		//응용가능..
		char startChar = 'A';
		char endChar = 'Z';
		int total = endChar-startChar;
		int diff = Math.abs(b-a);
		
		//바로갈때, 반대로 갈때 뭐가 가까운지 따져봄.
		if(diff<=total/2) {
			return diff;
		}else {
			return a-'A'+'Z'-b+1;
		}
	}
}
