package threads;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread extends Thread{
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private int length;
    private byte[] data;
    private int partName;

    public SendThread(DatagramSocket socket, InetAddress address, int port,byte[] data , int length, int partName) {
        this.socket = socket;
        this. data = data;
        this.address = address;
        this.port = port;
        this.length = length;
        this.partName = partName;
    }

    @Override
    public void run() {
        try{
            DatagramPacket packet = new DatagramPacket(data, length, address,port);
            socket.send(packet);

        }catch (Exception i){
            i.printStackTrace();
        }
    }
}
