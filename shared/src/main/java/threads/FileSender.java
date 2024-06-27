package threads;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static costant.Constant.PART_SIZE;

public class FileSender extends Thread {
 private File file;

 public FileSender(File file) {
  this.file = file;
 }

 public void sendPartFiles(InetAddress address, DatagramSocket socket, int port) {
  try (FileInputStream fileInputStream = new FileInputStream(file)) {
   int partNum = 1;
   int bytesRead;
   byte[] buffer = new byte[PART_SIZE];
   while ((bytesRead = fileInputStream.read(buffer)) != -1) {
    SendThread sendThread = new SendThread(socket, address, port, buffer, bytesRead, partNum);
    sendThread.start();
    Thread.sleep(5);

    partNum++;
   }
   Thread.sleep(1000);

   DatagramPacket end = new DatagramPacket("end".getBytes(), 3, address, port);
   socket.send(end);

  } catch (Exception e) {
   e.printStackTrace();
  }
  socket.close();
 }
}
