package gui;

import Requests.RequestHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login {
    private String username;
    private String password;
    public Login(){

        JFrame jFrame = new JFrame("client");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(75, 0 ,10, 0));

        JTextField username = new JTextField("enter username");
        this.username = username.getText();

        JTextField passworld = new JTextField("enter passworld");
        this.password = passworld.getText();

        JButton login = new JButton("Log in");
        login.addActionListener(e -> {
            RequestHandler.establishConnection("127.0.0.1", 1234);
            RequestHandler.handleLoginReq(this.username, this.password);
        });


    }
}
