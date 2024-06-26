package server;



import data.Data;
import responseHandler.LoginResponse;
import responseHandler.SignUpResponse;

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
                System.out.println("server is running....");
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
                    send.writeUTF(new LoginResponse(data).getResponse());
                    System.out.println("login");
                }
                case "signup" -> {
                    send.writeUTF(new SignUpResponse().getResponse());
                    System.out.println("signup");
                }
            }
            send.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
