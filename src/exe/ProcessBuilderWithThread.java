package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ProcessBuilderWithThread {
	
	
	public static void main(String[] args) {
		
		
		ExecutorService pool = Executors.newFixedThreadPool(2);

		ProcessBuilder processBuilder1 = new ProcessBuilder();

		// Run this on Windows, cmd, /c = terminate after this run
		processBuilder1.command("cmd.exe", "/c", "ping -n 3 google.com");
		
		ProcessBuilder processBuilder2 = new ProcessBuilder();

		// Run this on Windows, cmd, /c = terminate after this run
		processBuilder2.command("cmd.exe", "/c", "ping -n 3 naver.com");
		try {
			ProcessReadTask task1 = new ProcessReadTask(processBuilder1);
			ProcessReadTask task2 = new ProcessReadTask(processBuilder2);
			
			Future<List<String>> future1 = pool.submit(task1);
			Future<List<String>> future2 = pool.submit(task2);

			// no block, can do other tasks here
			System.out.println("process task1...");

			for (String s : future1.get()) {
				System.out.println(s);
			}
			
			for (String s : future2.get()) {
				System.out.println(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}

	private static class ProcessReadTask implements Callable<List<String>> {

		private ProcessBuilder processBuilder;

		public ProcessReadTask(ProcessBuilder processBuilder) {
			this.processBuilder = processBuilder;
		}

		@Override
		public List<String> call() {
			Process process=null;
			InputStream inputStream =null;
			try {
				process = processBuilder.start();
				inputStream = process.getInputStream();
				System.out.println("process ping...");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new BufferedReader(new InputStreamReader(inputStream))
					.lines()
					.collect(Collectors.toList());
		}
	}
}
