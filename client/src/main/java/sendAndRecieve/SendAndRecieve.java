package sendAndRecieve;

import threads.FileReceiver;
import threads.FileSender;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static costant.Constant.UDP;


public class SendAndRecieve {
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
            fileReceiver = new FileReceiver(socket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void establishConnection(String address){
        this.address = address;
        try{
            socket = new DatagramSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void send(File file){
        try {
            fileSender = new FileSender(file);
            fileSender.sendPartFiles(InetAddress.getByName(address), socket, UDP);
            while (true){
                if(fileSender.done){
                    socket.close();
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void receive(){
        try{

            fileReceiver.start();
            while (true){
                if(fileReceiver.done){
                    socket.close();
                    break;
                }
            }
        }catch (Exception u){
            u.printStackTrace();
        }
    }
}