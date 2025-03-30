import java.net.*;
import java.io.*;
import java.util.*;

public class Serveur_UDP {
    public static void main(String[] args) {
        DatagramSocket socket_udp;
        byte[] sendBuf = new byte[256];

        try {
            socket_udp = new DatagramSocket(55555);
        } catch (SocketException e) {
            System.out.println("Erreur de connexion");
            return;
        }

        while (true) {
            // Attendre le message émis par le client
            DatagramPacket message = new DatagramPacket(sendBuf, 256);
            try {
                socket_udp.receive(message);
            } catch (IOException e) {
                System.out.println("Erreur de socket_udp.receive()");
                e.printStackTrace();
                return;
            }

            // Envoyer un message sur ce port
            byte[] receiveBuf = new byte[256];

            System.out.println("Message received: " + new String(receiveBuf));
            message = new DatagramPacket(receiveBuf, 256);

            try {
                socket_udp.send(message);
            } catch (IOException e) {
                System.out.println("Erreur de socket_udp.send()");
                e.printStackTrace();
                return;
            }

            System.out.println(Thread.currentThread().getName() + ": send to " + message.getAddress() + ": port " + message.getPort());
        }
    }
}
