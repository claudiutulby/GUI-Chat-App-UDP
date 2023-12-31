public class Main {
    public static void main(String[] args) {
        ClientUDP udp = new ClientUDP("127.0.0.1", 5555);
        String username = ClientGUI.getUsername();
        if (username != null) {
            udp.send(username);
            ClientGUI gui = new ClientGUI();
            while (true)
                gui.receive();
        }
        udp.close();
    }
}
