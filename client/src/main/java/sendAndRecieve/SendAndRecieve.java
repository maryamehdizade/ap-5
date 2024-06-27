package sendAndRecieve;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

    }
}