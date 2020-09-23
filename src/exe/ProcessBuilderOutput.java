package exe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import file.util.FileUtil;

public class ProcessBuilderOutput {

	public static void main(String[] args) {

	}
	
	public void process(List<String> list) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		List<String> commandList = new ArrayList<String>();
		commandList.add("SIGNAGE.EXE");
		processBuilder.command(commandList);
		processBuilder.directory(new File("./"));

		// can also run the java file like this
		// processBuilder.command("java", "Hello");

		try {

			Process process = processBuilder.start();

			//Process에 값 전달(parameter아닌 scanner input형태)
			for (int i = 0; i < list.size(); i++) {
				process.getOutputStream().write(list.get(i).getBytes());
				process.getOutputStream().write(System.lineSeparator().getBytes());
				process.getOutputStream().flush();
			}
			BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            int exitCode = process.waitFor();
//            System.out.println("\nExited with error code : " + exitCode);
//
//			InputStream inputStream = process.getErrorStream();
//			BufferedReader stdError = new BufferedReader(new InputStreamReader(inputStream));
//
//			String line;
//			while ((line = stdError.readLine()) != null) {
//				System.out.println(line);
//			}
//
//			stdError.close();
//			int exitCode;
//			exitCode = process.waitFor();
//			System.out.println("\nExited with error code : " + exitCode);

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
