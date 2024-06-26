package gui;

import Requests.RequestHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static costant.Constant.FRAME_SIZE;
import static costant.Constant.PORT;

public class SignUp {
    private String username;
    private String password;
    public SignUp(){
        JFrame jFrame = new JFrame("client");
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(75, 0 ,10, 0));

        JTextField username = new JTextField("enter username");
        this.username = username.getText();

        JTextField passworld = new JTextField("enter passworld");
        this.password = passworld.getText();

        JButton login = new JButton("Sign up");
        login.addActionListener(e -> {
            RequestHandler.establishConnection("127.0.0.1", PORT);
            RequestHandler.handleSIgnUpReq(this.username, this.password);
        });
        jPanel.add(username);
        jPanel.add(passworld);
        jPanel.add(login);



        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
