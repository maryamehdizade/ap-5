package gui;

import model.MyUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;

import static costant.Constant.FRAME_SIZE;

public class Menu{
    final  File[] fileToUpload = new File[1];

    public Menu(MyUser user){
        JFrame jFrame = new JFrame(user.getUsername());
         jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout( jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JButton upload = new JButton("upload");
        JButton dow = new JButton("download");
        JButton chooseFile = new JButton("choose file");

        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel fileRow = new JPanel();
        fileRow.setLayout(new BoxLayout(fileRow, BoxLayout.Y_AXIS));

        for (File f : user.getFiles()) {
            JLabel fileName = new JLabel(f.getName());
            fileName.setFont(new Font("Ariel", Font.BOLD, 20));
            fileName.setBorder(new EmptyBorder(10, 0, 10, 0));

            fileRow.add(fileName);

        }
        //todo
        new Timer(1000, e -> {
            jFrame.revalidate();
        }).start();


        jPanel.add(fileRow);


        jFrame.add(upload);
        jFrame.add(dow);
        jFrame.add(chooseFile);
        jFrame.add(scrollPane);
        jFrame.setVisible(true);

        chooseFile.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("choose file to send");

            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fileToUpload[0] = fileChooser.getSelectedFile();
            }
        });

        dow.addActionListener(e->{

        });
    }

}
