package Requests;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler {
    private Socket socket;
    private DataOutputStream send;
    private DataInputStream recieve;
    public void establishConnection(String address, int port){
        try {
            socket = new Socket(address, port);
            send = new DataOutputStream(socket.getOutputStream());
            recieve = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleLoginReq(String username, String passworld){
        try{
            send.writeUTF("login:" + username + ":" + passworld);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
    public void handleSIgnUpReq(String username, String password){

    }
}
