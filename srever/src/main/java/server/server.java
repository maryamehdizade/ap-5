package server;



import data.Data;
import model.MyUser;
import responseHandler.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    ServerSocket serverSocket ;
    public server(int port){
        try{
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void listen(){
        Socket socket;
        while (true){
            try{
                socket = serverSocket.accept();
                connect(socket);

                new Data();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void connect(Socket socket){
        String massage;
        DataInputStream receive;
        DataOutputStream send;
        try{
            receive = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
            massage = receive.readUTF();
            String[] data = massage.split(":");
            switch (data[0]){
                case "login" -> {
                    send.writeUTF(Response.LoginResponse(data));
                }
                case "signup" -> {
                    send.writeUTF(Response.signUpResponse(data));
                }
                case "download" -> {
                    Response.downloadResponse(data[1]);
                }
                case "fileToDow" ->{
                    send.writeUTF(Response.fileToDow());
                }
                case "upload" -> {
                    Response.uploadResponse(data[1]);
                }
                case "uploadedFiles" -> {
                    send.writeUTF(Response.uploadedFile());
                }
                case  "name"-> send.writeUTF(MyUser.getINSTANCE().getUsername());
            }
            send.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
