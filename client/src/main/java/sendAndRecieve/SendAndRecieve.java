package sendAndRecieve;

import threads.FileReceiver;
import threads.FileSender;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static costant.Constant.PORT;

public class SendAndRecieve {
    private DatagramSocket socket;
    private int port;
    private String address;

    public void establishConnection(String address, int port){
        this.address = address;
        this.port = port;
        try{
           socket = new DatagramSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void send(File file){
        try {
            FileSender fileSender = new FileSender(file);
            fileSender.sendPartFiles(InetAddress.getByName(address), socket, PORT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void receive(){
        try{
            FileReceiver fileReceiver = new FileReceiver(socket);
            fileReceiver.start();
        }catch (Exception u){
            u.printStackTrace();
        }
    }
}