package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login {
    public Login(){
        JFrame jFrame = new JFrame("client");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(75, 0 ,10, 0));

        JTextField textField = new JTextField("enter username");

    }
}
