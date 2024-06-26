package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menu {
    public Menu(){
        //todo:add user name
        JFrame jFrame = new JFrame();
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JButton upload = new JButton("upload");
        JButton dow = new JButton("download");

        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jFrame.add(upload);
        jFrame.add(dow);
        jFrame.add(scrollPane);
        jFrame.setVisible(true);
    }
}
