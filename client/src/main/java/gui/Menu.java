package gui;

import Requests.RequestHandler;
import model.MyUser;
import sendAndRecieve.SendAndRecieve;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import static costant.Constant.FRAME_SIZE;
import static costant.Constant.PORT;

public class Menu {
    final File[] fileToUpload = new File[1];
    JScrollPane scrollPane;
    JPanel fileRow;
    JPanel jPanel;
    JFrame jFrame;

    public Menu() {
        jFrame = new JFrame(MyUser.getINSTANCE().getUsername());
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JButton upload = new JButton("upload");
        JButton dow = new JButton("download");
        JButton chooseFile = new JButton("choose file to upload");

        JButton uploadedFiles = new JButton("uploaded files");
        uploadedFiles.addActionListener(e -> RequestHandler.handleUploadedFiles());


        jFrame.add(upload);
        jFrame.add(dow);
        jFrame.add(chooseFile);
        jFrame.add(uploadedFiles);
        jFrame.setVisible(true);

        chooseFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fileToUpload[0] = fileChooser.getSelectedFile();

            }
        });

        dow.addActionListener(e -> {
            String fileToDow = showFiles();
            if(fileToDow != null) {
                RequestHandler.establishConnection("127.0.0.1", PORT);
                RequestHandler.handleDowReq(fileToDow);
                RequestHandler.end();
                SendAndRecieve recieve = new SendAndRecieve();
                recieve.establishConnection("127.0.0.1", PORT);
                recieve.fileReceiver.path = "client";
                recieve.fileReceiver.fileName = fileToDow;
                recieve.receive();
            }
        });
        upload.addActionListener(e -> {
            if(fileToUpload[0] != null){
                RequestHandler.establishConnection("127.0.0.1", PORT);
                RequestHandler.handleUploadReq(fileToUpload[0].getName());
                RequestHandler.end();
                SendAndRecieve send = new SendAndRecieve();
                send.establishConnection("127.0.0.1", PORT);
                send.send(fileToUpload[0]);
                if(send.done){
                    MyUser.getINSTANCE().getFiles().add(fileToUpload[0]);
                }
            }
        });
    }
    private void showUploadedFiles(){
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        fileRow = new JPanel();
        fileRow.setLayout(new BoxLayout(fileRow, BoxLayout.Y_AXIS));

        for (File f : MyUser.getINSTANCE().getFiles()) {
            JLabel fileName = new JLabel(f.getName());
            fileName.setFont(new Font("Ariel", Font.BOLD, 20));
            fileName.setBorder(new EmptyBorder(10, 0, 10, 0));

            fileRow.add(fileName);
        }

        panel.add(fileRow);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    public String showFiles(){
        RequestHandler.establishConnection("127.0.0.1", PORT);
        String[] fileNames = RequestHandler.handledowFiles().split("/");
        JFrame filesForDow = new JFrame();
        filesForDow.setSize(300,300);
        filesForDow.setLayout(new BoxLayout(filesForDow.getContentPane(), BoxLayout.Y_AXIS));

        for (int i = 0; i < fileNames.length; i++) {
            JLabel button = new JLabel(fileNames[i]);
            filesForDow.add(button);
        }
        JButton back = new JButton("back");
        back.addActionListener(e -> {
            filesForDow.dispose();
        });

        JTextField field = new JTextField("enter the name of the file you want to download");

        String[] toDow = new String[1];

        JButton dow = new JButton("download");
        dow.addActionListener(e -> {
             toDow[0] = field.getText();
             filesForDow.dispose();
        });
        filesForDow.add(field);
        filesForDow.add(dow);
        filesForDow.add(back);
        filesForDow.setVisible(true);

        if(toDow[0] != null)filesForDow.dispose();
        if(!field.getText().equals("enter the name of the file you want to download"))
            return field.getText();
        return null;
    }


}
