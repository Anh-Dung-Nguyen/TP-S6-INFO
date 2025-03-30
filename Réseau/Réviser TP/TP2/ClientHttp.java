import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHttp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 55555;
        String file = "index.html";

        if (args.length >= 1) {
            host = args[0];
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            file = args[2];
        }

        try {
            Socket socket = new Socket(host, port);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String request = "GET " + file + " HTTP/1.1\r\n\r\n";
            socket.getOutputStream().write(request.getBytes());

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
