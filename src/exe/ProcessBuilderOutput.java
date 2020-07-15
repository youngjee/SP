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

		ProcessBuilder processBuilder = new ProcessBuilder();

		FileUtil.createDirectory("./OUTFILE");

		List<String> commandList = new ArrayList<String>();
		commandList.add("SIGNAGE.EXE");
		processBuilder.command(commandList);
		processBuilder.directory(new File("./"));

		// can also run the java file like this
		// processBuilder.command("java", "Hello");

		try {

			Process process = processBuilder.start();

			process.getOutputStream().write("10:59:56#BUS01,02130#BUS02,00855#BUS03,07485".getBytes());
			process.getOutputStream().flush();

			InputStream inputStream = process.getErrorStream();
			BufferedReader stdError = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = stdError.readLine()) != null) {
				System.out.println(line);
			}

			stdError.close();
			int exitCode;
			exitCode = process.waitFor();
			System.out.println("\nExited with error code : " + exitCode);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
