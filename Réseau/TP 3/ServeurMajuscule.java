import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;

class ServeurMajuscule {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur " + InetAddress.getLocalHost().getHostAddress() + " port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connecté: " + clientSocket.getInetAddress());
                    pw.println("Bienvenue sur le serveur majuscule. Envoyez une ligne de texte, '.' pour quitter.");

                    String inputLine;
                    while ((inputLine = br.readLine()) != null) { // Lecture des messages du client
                        if (".".equals(inputLine)) {
                            pw.println("Session terminée.");
                            break;
                        }
                        pw.println(inputLine.toUpperCase()); // Conversion en majuscule et réponse
                    }

                    System.out.println("Client déconnecté: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    System.err.println("Erreur de communication avec un client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur: " + e.getMessage());
        }
    }
}