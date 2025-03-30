import java.net.*;
import java.io.*;
import java.util.*;

public class ClientMajuscule {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 55555;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            for (int i = 0; i < 3; i ++) {
                System.out.println(in.readLine());
            }

            for (String arg : args) {
                out.println(arg);
                System.out.println(in.readLine());
            }

            out.println(".");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
