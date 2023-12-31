import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientGUI extends JFrame implements ActionListener {
    final static JTextArea chat = new JTextArea();
    final static JTextPane username = new JTextPane();
    final static JTextField sendtext = new JTextField();
    final static JPanel panel = new JPanel(null);

    final static JButton button = new JButton("Dă-i");
    final static ClientUDP client = new ClientUDP("127.0.0.1", 5555);
    private static String nume;

    ClientGUI() {
        this.setTitle("Chat - " + nume);
        this.setBounds(500,150, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chat.setBounds(10, 10, 480, 400);
        chat.setEditable(false);
        chat.setFocusable(false);
        sendtext.setBounds(70, 420, 340, 20);
        sendtext.addActionListener(this);
        username.setBounds(10, 417, 50, 20);
        username.setOpaque(false);
        username.setFocusable(false);
        button.setFocusable(false);
        button.setBounds(420, 420, 70, 20);
        button.addActionListener(this);
        panel.add(chat);
        panel.add(sendtext);
        panel.add(username);
        panel.add(button);
        this.add(panel);
        this.setVisible(true);
    }

    // Fereastra inițială, care cere utilizatorului să-și aleagă un nume
    public static String getUsername() {
        nume = JOptionPane.showInputDialog("Numele de utilizator: ");
        username.setText(nume);
        return nume;
    }

    public static void receive() {
        String receivedMessage = client.receive();
        if (!Objects.equals(receivedMessage, "")) {
            chat.append(receivedMessage + '\n');
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textToSend = nume + ": " + sendtext.getText();
        sendtext.setText("");
        ClientUDP.send(textToSend);
    }
}
