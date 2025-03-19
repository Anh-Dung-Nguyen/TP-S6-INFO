import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServeurMajusculePlusOuMoins {
    public static void main(String[] args) throws IOException {
        int port = 8000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Nouvelle connexion sur " + InetAddress.getLocalHost().getHostAddress() + " port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Nouvelle connexion");

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    Random r = new Random();
                    int n = r.nextInt(2);

                    while (true) {
                        try {
                            int guess = Integer.parseInt(in.readLine());

                            if (guess < n) {
                                out.println("Inférieur");
                            } else if (guess > n) {
                                out.println("Supérieur");
                            } else {
                                out.println("Egal");
                                break;
                            }
                        } catch (IOException e) {
                            System.err.println("Erreur de lire la chiffre: " + e.getMessage());
                            socket.close();
                        }
                        socket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Erreur de communication avec un client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur: " + e.getMessage());
        }
    }
}
