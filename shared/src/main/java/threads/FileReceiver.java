package threads;

import model.MyUser;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import static costant.Constant.PART_SIZE;

public class FileReceiver extends Thread{
    private byte[] data;
    private int size;
    private String fileName;
    private final DatagramSocket socket;

    public FileReceiver(DatagramSocket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            while (true){
                byte[] data = new byte[PART_SIZE];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);


                this.data = packet.getData();
                this.size = packet.getLength();

                if(Arrays.equals(data, "end".getBytes())){
                    socket.close();
                    break;
                }

                receiveFile();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void receiveFile() {
        try{
            String filePath = "srever/src/main/java/data/userFiles";
            File fileToReceive = new File(filePath + "/" + MyUser.getINSTANCE().getUsername());
            if(!fileToReceive.isDirectory() || !fileToReceive.exists())fileToReceive.mkdir();
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileToReceive.getPath() + "/" + fileName, true)) {
                fileOutputStream.write(data, 0, size);
            }
        }catch (Exception i){
            i.printStackTrace();
        }
    }
}
