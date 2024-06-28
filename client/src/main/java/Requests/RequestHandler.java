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
