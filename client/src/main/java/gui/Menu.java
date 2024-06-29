package gui;

import Requests.RequestHandler;
import model.MyUser;
import sendAndRecieve.SendAndRecieve;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;

import static costant.Constant.*;

public class Menu {
    private String user;
    final File[] fileToUpload = new File[1];
    String[] toDow = new String[1];
    JScrollPane scrollPane;
    JPanel fileRow;
    JPanel jPanel;
    JFrame jFrame;

    public Menu() {

        RequestHandler.establishConnection("127.0.0.1", PORT);
        user = RequestHandler.getName();
        RequestHandler.end();

        jFrame = new JFrame(user);
        jFrame.setSize(FRAME_SIZE, FRAME_SIZE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JButton upload = new JButton("upload");
        JButton dow = new JButton("download");
        JButton chooseFile = new JButton("choose file to upload");

        JButton uploadedFiles = new JButton("uploaded files");
        uploadedFiles.addActionListener(e -> {
            RequestHandler.establishConnection("127.0.0.1", PORT);
            String response = RequestHandler.handleUploadedFiles();
            RequestHandler.end();

            assert response != null;
            showUploadedFiles(response);
        });


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
            RequestHandler.establishConnection("127.0.0.1", PORT);
            String[] fileNames = RequestHandler.handledowFiles().split("/");
            RequestHandler.end();

            downloadFrame(fileNames);
        });
        upload.addActionListener(e -> {
            if(fileToUpload[0] != null){
                RequestHandler.establishConnection("127.0.0.1", PORT);
                RequestHandler.handleUploadReq(fileToUpload[0].getName());
                RequestHandler.end();
                SendAndRecieve send = new SendAndRecieve();
                send.establishConnection("127.0.0.1");
                send.send(fileToUpload[0]);
            }
        });
    }
    private void downloadFrame(String[] fileNames){
        JFrame filesForDow = new JFrame();
        filesForDow.setSize(300,300);
        filesForDow.setLayout(new BoxLayout(filesForDow.getContentPane(), BoxLayout.Y_AXIS));
        filesForDow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < fileNames.length; i++) {
            JLabel button = new JLabel(fileNames[i]);
            filesForDow.add(button);
        }
        JButton back = new JButton("back");
        back.addActionListener(s -> {
            filesForDow.dispose();
        });

        JTextField field = new JTextField("enter the name of the file you want to download");

        String[] toDow = new String[1];

        JButton down = new JButton("download");
        down.addActionListener(a -> {

            toDow[0] = field.getText();
            RequestHandler.establishConnection("127.0.0.1", PORT);
            RequestHandler.handleDowReq(toDow[0]);
            RequestHandler.end();


            recieving(toDow[0]);
            filesForDow.dispose();

        });
        filesForDow.add(field);
        filesForDow.add(down);
        filesForDow.add(back);
        filesForDow.setVisible(true);
    }
    private void recieving(String fileName){
        SendAndRecieve recieve = new SendAndRecieve();
        recieve.establishConnection("127.0.0.1", UDP);
        recieve.fileReceiver.path = "C:\\Users\\EPSILON\\IdeaProjects\\tamrin5\\client\\src\\main\\java\\data\\userFiles";
        recieve.fileReceiver.fileName = fileName;
        recieve.fileReceiver.userName = user;
        recieve.receive();


    }
    private void showUploadedFiles(String response){
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        fileRow = new JPanel();
        fileRow.setLayout(new BoxLayout(fileRow, BoxLayout.Y_AXIS));

        if(response != null) {

            String[] files = response.split("/");

            for (String s : files) {
                JLabel fileName = new JLabel(s);
                fileName.setFont(new Font("Ariel", Font.BOLD, 20));
                fileName.setBorder(new EmptyBorder(10, 0, 10, 0));

                fileRow.add(fileName);
            }
        }

        panel.add(fileRow);
        frame.add(scrollPane);
        frame.setVisible(true);
    }


}
