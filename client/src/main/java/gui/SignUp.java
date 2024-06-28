package gui;

import Requests.RequestHandler;
import model.MyUser;

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


        JTextField passworld = new JTextField("enter passworld");


        JButton login = new JButton("Sign up");
        login.addActionListener(e -> {
            this.username = username.getText();
            this.password = passworld.getText();
            RequestHandler.establishConnection("127.0.0.1", PORT);
            if(RequestHandler.handleSIgnUpReq(this.username, this.password)){
                jFrame.dispose();
                new Menu();
            }
            RequestHandler.end();
        });
        JButton back = new JButton("back");
        back.addActionListener(e -> {
            jFrame.dispose();
            new Start();
        });

        jPanel.add(username);
        jPanel.add(passworld);
        jPanel.add(login);
        jPanel.add(back);



        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
