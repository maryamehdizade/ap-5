package util;

import threads.FileSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


import static costant.Constant.PART_SIZE;

public class Splitter {
    public static void splitAndSendFile(File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[PART_SIZE];
            int bytesRead;
            int partNumber = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                String partName = file.getName() + "Part" + partNumber;
                FileOutputStream fileOutputStream = new FileOutputStream(partName);
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.close();

                //sendFile


                partNumber++;

            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
    private void send(DatagramSocket socket, InetAddress address, int port, byte[] data , int length, int partName){
        try{
            DatagramPacket packet = new DatagramPacket(data, length, address,port);
            socket.send(packet);

        }catch (Exception i){
            i.printStackTrace();
        }
    }
}
