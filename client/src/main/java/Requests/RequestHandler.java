package Requests;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler {
    private static Socket socket;
    private static DataOutputStream send;
    private static DataInputStream recieve;
    public static void establishConnection(String address, int port){
        try {
            socket = new Socket(address, port);
            send = new DataOutputStream(socket.getOutputStream());
            recieve = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void handleLoginReq(String username, String password){
        try{
            send.writeUTF("login:" + username + ":" + password);
            String response = recieve.readUTF();

        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
    public void handleSIgnUpReq(String username, String password){
        try{
            send.writeUTF("signup:" + username + ":" + password);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
