package responseHandler;

import data.Data;
import model.MyUser;
import threads.FileSender;
import util.Hasher;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import static costant.Constant.PORT;
import static util.Util.createNewUser;
import static util.Util.getFile;

public class Response {
    static String response;
    static boolean done;
    public  static String LoginResponse(String[] data){
        if(data[1] == null ||data[2] == null){
            response = "enter username and passworld";
            done = true;
        }if(!done){
            for (MyUser u :
                    new Data().getUsers()) {
                if(data[1].equals(u.getUsername())){
                    System.out.println(u.getPassword());
                    if(Hasher.hashPassword(data[2]).equals(u.getPassword())){
                        response = "access granted";
                        MyUser.setINSTANCE(u);
                        //todo
                        MyUser.getINSTANCE().setFiles(u.getFiles());
                        done = true;
                        break;
                    }else {
                        response = "wrong password";
                        done = true;
                        break;
                    }
                }
            }
            if(!done)response = "username not found";
        }
        done = false;
        return response;
    }
    public static String signUpResponse(String[] data){
        for (MyUser u :
                new Data().getUsers()) {
            if(data[1].equals(u.getUsername())){
                response = "this username is taken";
                done = true;
                break;
            }
        }if(!done){
            response = "welcome";
            MyUser.setINSTANCE(createNewUser(data[1], data[2]));
        }
        return response;
    }
    public static void downloadResponse(String fileName) {
        try {
            File file = getFile(fileName);
            if(file != null) {
                FileSender fileSender = new FileSender(file);
                fileSender.sendPartFiles(InetAddress.getByAddress("localhost".getBytes()), new DatagramSocket(), PORT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
