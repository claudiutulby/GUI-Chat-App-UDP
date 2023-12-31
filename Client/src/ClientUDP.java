import java.io.*;
import java.net.*;

class ClientUDP {
    private static InetAddress serverAddress;
    private static int serverPort;
    private static DatagramSocket clientSocket;

    // Inițializăm clientul udp
    ClientUDP(String serverAddress, int serverPort) {
        try {
            ClientUDP.serverAddress = InetAddress.getByName(serverAddress);
            ClientUDP.serverPort = serverPort;
            clientSocket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Primim un string pe care-l tranformăm în bytes și-l trimitem server-ului
    public static void send(String message) {
        try {
            byte[] bytes = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Primim un șir de bytes pe care-l decodificăm și-l returnăm gui-ului pentru a-l afișa
    public static String receive() {
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            return receivedMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        if (clientSocket != null) {
            clientSocket.close();
        }
    }
}
