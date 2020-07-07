package exe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessBuilderDirectory {
	public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        List<String> commandList = new ArrayList<String>();
        commandList.add("cmd.exe");
        commandList.add("/c");
        commandList.add("dir");
        processBuilder.command(commandList);
        processBuilder.directory(new File("C:\\users"));

        // can also run the java file like this
        // processBuilder.command("java", "Hello");

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
