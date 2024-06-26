package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static costant.Constant.FRAME_SIZE;

public class Client {
    public Client(){
        JFrame jFrame = new JFrame("client");
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(75, 0 ,10, 0));

        JButton send = new JButton("send File");
        send.setPreferredSize(new Dimension(150, 75));

        JButton chooseFile = new JButton("chooseFile");
        chooseFile.setPreferredSize(new Dimension(150 ,75));

        jPanel.add(send);
        jPanel.add(chooseFile);

        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
