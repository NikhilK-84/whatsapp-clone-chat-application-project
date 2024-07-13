import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener {
    // global declarations
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    Server() {
        f.setLayout(null); // removes the default bounds/layout

        // code for green upmost first panel p1
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(51, 153, 255));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1); // add p1 to frame

        // code for label - 1 (back arrow)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")); // image converted to icon
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT); // scale the image to required pixel
        ImageIcon i3 = new ImageIcon(i2); // convert image to imageicon as JLabel dosen't take Image as a parameter.
        JLabel back = new JLabel(i3); // icon converted to a label
        back.setBounds(5, 20, 25, 25);
        p1.add(back); // add back label to panel

        // feedback when mouse is clicked on the icon/label - 1 (back arrow)
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // code for label - 2 (profile pic)
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // code for label - 3 (video call icon)
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);

        // code for label - 4 (phone call icon)
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 30, 30);
        p1.add(phone);

        // code for label - 5 (three dots icon)
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(15, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel menu = new JLabel(i15);
        menu.setBounds(410, 20, 15, 30);
        p1.add(menu);

        // code for label - 6 (For name)
        JLabel name = new JLabel("Iron-man");
        name.setBounds(110, 15, 100, 18);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18)); // new Font is a parameterized anonymous constructor
        name.setForeground(Color.WHITE);
        p1.add(name);

        // code for label - 6 (Status Online/Offline)
        JLabel status = new JLabel("Active-now");
        status.setBounds(110, 35, 100, 18);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14)); // new Font is a parameterized anonymous constructor
        status.setForeground(Color.WHITE);
        p1.add(status);

        // code for second panel - a1
        a1 = new JPanel();
        a1.setBackground(new Color(0, 0, 153));
        a1.setBounds(5, 75, 440, 570);
        f.add(a1); // add panel to frame

        // code for type-text Text Field
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text); // add to frame

        // code for send button
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this); // implements actionPerformed function
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        // code for base frame
        f.setSize(450, 700);
        f.setLocation(200, 30);
        f.setUndecorated(true); // removes the default close and minimize button java header
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();

            JPanel p2 = formatLabel(out); // formtLabel is our own function. return type - JPanel, input parameter -
            // string

            a1.setLayout(new BorderLayout()); // use one of the pre-defined layout i.e. BorderLayout

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);

            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15)); // creates a vertical thing, which can take multiple 'right'
                                                       // panels
            // with a vertical gap of 15.

            a1.add(vertical, BorderLayout.PAGE_START); // add vertical box to panel a1

            dout.writeUTF(out); // this line is from io stream library that needs the code to be in try-catch.
                                // So whole code is put in try-catch..

            text.setText("");

            // these three functions will help in refreshing the frame every time an action
            // is done. Functions of JFrame
            f.repaint();
            f.invalidate();
            f.validate();
            // these...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Server(); // anonymous object created

        // exception handeling using try-catch
        try {
            ServerSocket skt = new ServerSocket(6001); // local host server..

            while (true) {
                Socket s = skt.accept(); // accepts the data from what server accepts..
                DataInputStream din = new DataInputStream(s.getInputStream()); // stream is like a scanner. this is
                                                                               // input stream
                dout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = din.readUTF(); // implements utf protocol (the format of sent data should be same as
                                                // the recieved)

                    JPanel panel = formatLabel(msg);
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);

                    vertical.add(left);
                    f.validate(); // refresh the frame
                }
            }
        } catch (Exception e) { // e is object
            e.printStackTrace(); // whatever exception (e) has come will be printed/displayed..
        }
    }
}
