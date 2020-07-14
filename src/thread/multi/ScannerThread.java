package thread.multi;

import java.util.Scanner;

public class ScannerThread implements Runnable {

	private MultiThreadExample mt = new MultiThreadExample();

	public ScannerThread(MultiThreadExample mt) {
		this.mt = mt;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
			System.out.print("VALUE : ");

			// 값 받기
			String value = scanner.nextLine();
			mt.getStrList().add(value);

			// 종료 문자면 break
			if ("Q".equals(value))
				break;

			// 값 처리
		}

		scanner.close();
	}

}
