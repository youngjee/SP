package socket.multi.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer implements Runnable {

    private Socket socket;

    public EchoServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        PrintWriter out = null;
        BufferedReader in = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String echo;
            while ((echo = in.readLine()) != null) {
                System.out.println("echo: " + echo + " (from " + socket.getPort() + ")");
                out.println(echo);
                out.flush();

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();

            }

        }
    }

         

    public static void main(String[] args) throws IOException {

        int portNumber = 30000;

        ServerSocket serverSocket = new ServerSocket(portNumber);
        ExecutorService es = Executors.newFixedThreadPool(5);
        while (true) {
            es.submit(new EchoServer(serverSocket.accept()));
        }

        //serverSocket.close();

    }

}