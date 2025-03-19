import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMajuscule {
    public static void main(String[] args) throws IOException {
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            Socket socket = new Socket(host, port);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connecté au serveur " + host + " sur le port " + port);

            System.out.println("Serveur: " + br.readLine());

            for (int i = 2; i < args.length; i++) {
                pw.println(args[i]);
                System.out.println("Serveur: " + br.readLine());
            }

            pw.println(".");
            System.out.println("Serveur: " + br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
