package template;

import java.util.List;
import java.util.Scanner;

public class RunManager {
	public static void main(String[] args) {

		new RunManager().run();
	}
	
	private DataReader reader;
	private DataProcessor processor;
	private DataWriter writer;
	
	public void run() {
		//콘솔 입력하기
		Scanner scanner = new Scanner(System.in);
		System.out.print("VALUE : ");// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
		
		String value = scanner.nextLine();
		scanner.close();
		
		
		//파일 읽기
		List<String> dataList = reader.read(value, "./INPUT/INFILE.TXT");
		//데이터 가공
		dataList = processor.process(dataList);
		//데이터 파일에 쓰기
		writer.write("./OUTPUT/OUTFILE.TXT", dataList);
		
	}

}
