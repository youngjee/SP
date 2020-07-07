package exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RunProcessBuilder {
	public static void main(String[] args) {
		
		List<String> command = new ArrayList<String>();
		command.add("ls");
		command.add("-l");
		command.add("/");
		
		ProcessBuilder processBuilder = new ProcessBuilder(command);

		try {
		    System.out.println("run " + command.toString());

		    Process process = processBuilder.start();

		    BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		    String line;
		    while ((line = outReader.readLine()) != null) {
		        System.out.println(line);
		    }
		    while ((line = errorReader.readLine()) != null) {
		        System.out.println(line);
		    }

		    int exitCode = process.waitFor();
		    System.out.println("\nExited with error code : " + exitCode);
		} catch (IOException ex) {
		    ex.printStackTrace();
		} catch (Exception ex) {
		    ex.printStackTrace();
		} finally {
		    ;
		}
	}
}
