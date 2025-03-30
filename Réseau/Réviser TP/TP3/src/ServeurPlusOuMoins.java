import java.net.*;
import java.io.*;
import java.util.*;

public class ServeurPlusOuMoins {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(55555);

        try {
            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Nouvelle connextion");

                // Récupérer une ligne tapée au clavier
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                int nbRandom = (int) Math.ceil(Math.random() * 100);

                while (true) {
                    try {
                        int guess = Integer.parseInt(in.readLine());

                        if (guess < nbRandom) {
                            out.println("1");
                        } else if (guess > nbRandom) {
                            out.println("-1");
                        } else {
                            out.println("0");
                            socket.close();
                            break;
                        }
                    } catch (NumberFormatException e) {
                        out.println("NumberFormatException");
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
