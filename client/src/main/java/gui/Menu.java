package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.File;

import static costant.Constant.FRAME_SIZE;

public class Menu {
    final  File[] fileToUpload = new File[1];

    public Menu(){
        //todo:add user name
        JFrame jFrame = new JFrame();
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
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

        upload.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("choose file to send");

            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fileToUpload[0] = fileChooser.getSelectedFile();
            }
        });
    }
}
