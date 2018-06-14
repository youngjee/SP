package console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderInput {
	public static void main(String[] args) {
		try {

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			String s = "";
			System.out.println("끝내고 싶다면 ctrl+c를 입력하시오");// ctrl+c
			// while(!(s=br.readLine()).equals("@esc")){//@esc
			while ((s = br.readLine()) != null) {// ctrl+c
				System.out.println(s);// 출력
			}
			br.close();
			isr.close();

		} catch (Exception ee) {
			System.out.println(ee.toString());
		}
	}
}
