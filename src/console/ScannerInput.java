package console;

import java.util.Scanner;

public class ScannerInput {
	public static void main(String[] args) {

	}

	// 1개 입력받기
	public static void oneInput() {
		Scanner scanner = new Scanner(System.in);
		// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
		System.out.print("VALUE : ");
		// 값 받기
		String value = scanner.nextLine();
		scanner.close();
		// 값 처리
	}

	// 계속 입력받기
	public static void continueInput() {

		Scanner scanner = new Scanner(System.in);
		while (true) {
			// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
			System.out.print("VALUE : ");

			// 값 받기
			String value = scanner.nextLine();

			// 종료 문자면 break
			if ("q".equals(value))
				break;

			// 값 처리
		}

		scanner.close();

	}
}
