package sendAndRecieve;

import threads.FileReceiver;
import threads.FileSender;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SendAndRecieve {
    public boolean done;
    private DatagramSocket socket;
    private int port;
    private String address;
    public FileSender fileSender;
    public FileReceiver fileReceiver;

    public  void establishConnection(String address, int port){
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
            fileSender = new FileSender(file);
            fileSender.sendPartFiles(InetAddress.getByName(address), socket, port);
            done = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void receive(){
        try{
            fileReceiver = new FileReceiver(socket);
            fileReceiver.start();
            done = true;
        }catch (Exception u){
            u.printStackTrace();
        }
    }
}