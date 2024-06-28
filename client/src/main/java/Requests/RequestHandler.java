package Requests;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler {
    private static Socket socket;
    private static DataOutputStream send;
    private static DataInputStream receive;
    public static void establishConnection(String address, int port){
        try {
            socket = new Socket(address, port);
            send = new DataOutputStream(socket.getOutputStream());
            receive = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean handleLoginReq(String username, String password){
        try{
            send.writeUTF("login:" + username + ":" + password);
            String response = receive.readUTF();
            System.out.println(response);
            return response.startsWith("access granted");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }

        return false;
    }
    public static boolean handleSIgnUpReq(String username, String password){
        try{
            send.writeUTF("signup:" + username + ":" + password);
            String response = receive.readUTF();
            return response.startsWith("welcome");

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return false;
    }
    public static void handleDowReq(String fileName){
        try{
            send.writeUTF("download:" +fileName);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void handleUploadedFiles(){
        try{
            send.writeUTF("uploadedFiles");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void handleUploadReq(String fileName){
        try{
            send.writeUTF("upload:" +fileName);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static String handledowFiles(){
        try{
            send.writeUTF("fileToDow");
           String fileNames =  receive.readUTF();
           return fileNames;
        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }
    }

    public static void end(){
        try{
            socket.close();
            receive.close();
            send.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
