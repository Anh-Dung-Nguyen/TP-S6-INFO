import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientPlusOuMoins {
    public static void main(String[] args) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            int port = Integer.parseInt(args[1]);
            Socket socket = new Socket(host, port);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while ((input = clavier.readLine()) != null) {
                out.println(input);
                switch (in.readLine()) {
                    case "1":
                        System.out.println("Trop petit");
                        break;
                    case "-1":
                        System.out.println("Trop grand");
                        break;
                    case "0":
                        System.out.println("Gagné");
                        socket.close();
                        return;
                    default:
                        System.out.println("Entrez un nombre valide");
                        break;
                }
            }
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
