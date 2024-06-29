package threads;

import model.MyUser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import static costant.Constant.PART_SIZE;
import static costant.Constant.UDP;

public class FileReceiver extends Thread{
    private byte[] data;
    private int size;
    public String fileName;
    public String userName;
    public String path ;
    private final DatagramSocket socket;
    public boolean done;

    public FileReceiver(DatagramSocket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
//            frame = new JFrame();
//            frame.setSize(300, 100);
//            frame.setLayout(null);
//            JButton button = new JButton();
//            button.setSize(50,10);
//            button.setLocation(25, 10);
//            button.setBackground(Color.black);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    button.setSize(button.getWidth() + 2,button.getHeight());
//                }
//            }).start();
//            frame.add(button);
//            frame.setVisible(true);


            while (true){
                byte[] data = new byte[PART_SIZE];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);


                this.data = packet.getData();
                this.size = packet.getLength();

                if(size == 0){
                    System.out.println("received successfully");
                    break;
                }

                receiveFile();
            }
            socket.close();
            done = true;
//            frame.dispose();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private JFrame frame;

    public void receiveFile() {
        try{
            File fileToReceive = new File(path  + "\\" + userName);
            if(!fileToReceive.exists())fileToReceive.mkdir();
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileToReceive.getPath() + "\\" + fileName, true)) {
                fileOutputStream.write(data, 0, size);
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
}
