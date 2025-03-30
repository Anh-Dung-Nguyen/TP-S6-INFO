import java.net.*;
import java.io.*;
import java.util.*;

public class ServeurMajuscule {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(55555);

        try {
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream out = new PrintStream(socket.getOutputStream());

                out.println("Bienvenue sur ce serveur de mise en majuscule.\\n\" +\n" +
                        "\"Entrez un texte ligne par ligne, il sera converti.\\n\" +\n" +
                        "\"Tapez '.' pour terminer.");

                String line = in.readLine();

                while (!line.equals(".")) {
                    out.println(line.toUpperCase());
                    line = in.readLine();
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
