import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import gui.Login;
import gui.SignUp;

import static costant.Constant.FRAME_SIZE;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("client");
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(75, 0 ,10, 0));

        JButton signup = new JButton("sign up");
        signup.setPreferredSize(new Dimension(150, 75));
        signup.addActionListener(e -> {
            new SignUp();
            jFrame.dispose();
        });

        JButton login = new JButton("log in");
        login.setPreferredSize(new Dimension(150 ,75));
        login.addActionListener(e -> {
            new Login();
            jFrame.dispose();
        });
        jPanel.add(signup);
        jPanel.add(login);

        jFrame.add(jPanel);
        jFrame.setVisible(true);

    }
}
