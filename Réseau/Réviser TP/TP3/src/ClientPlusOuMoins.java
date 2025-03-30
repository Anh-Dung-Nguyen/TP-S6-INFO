import java.io.*;
import java.net.*;
import java.util.*;

public class ClientPlusOuMoins {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 55555;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdIn.readLine()) != null) {
                out.println(input);
                switch (in.readLine()) {
                    case "1":
                        System.out.println("Trop petit");
                        break;
                    case "-1":
                        System.out.println("Trop grand");
                        break;
                    case "0":
                        System.out.println("Bravo");
                        socket.close();
                        break;
                    case "NumberFormatException":
                        System.out.println("Choose a valid number");
                        break;
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
