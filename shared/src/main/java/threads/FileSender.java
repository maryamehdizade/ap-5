package threads;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static costant.Constant.PART_SIZE;

public class FileSender extends Thread {

 private File file;
 public boolean done;

 public FileSender(File file) {
  this.file = file;
 }

 public void sendPartFiles(InetAddress address, DatagramSocket socket, int port) {
  try (FileInputStream fileInputStream = new FileInputStream(file)) {
   int partNum = 1;
   int bytesRead;
   byte[] buffer = new byte[PART_SIZE];
   while ((bytesRead = fileInputStream.read(buffer)) != -1) {
    send(socket, address, port, buffer, bytesRead, partNum);
    Thread.sleep(5);

    partNum++;
   }
   Thread.sleep(1000);

   byte[] e = new byte[0];
   DatagramPacket end = new DatagramPacket(e, 0, address, port);
   socket.send(end);
   done = true;
   System.out.println("done");


  } catch (Exception e) {
   e.printStackTrace();
  }
  socket.close();
 }
 private void send(DatagramSocket socket, InetAddress address, int port,byte[] data , int length, int partName){
  try{
   DatagramPacket packet = new DatagramPacket(data, length, address,port);
   socket.send(packet);

  }catch (Exception i){
   i.printStackTrace();
  }
 }
}
