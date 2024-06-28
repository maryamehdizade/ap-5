package responseHandler;

import data.Data;
import model.MyUser;
import threads.FileReceiver;
import threads.FileSender;
import util.Hasher;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static costant.Constant.PART_SIZE;
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
                    Data.getINSTANCE().getUsers()) {
                if(data[1].equals(u.getUsername())){
                    if(Hasher.hashPassword(data[2]).equals(u.getPassword())){
                        response = "access granted";
                        MyUser.setINSTANCE(u);
                        //todo
                        MyUser.getINSTANCE().setFiles(u.getFiles());
                    }else {
                        response = "wrong password";
                    }
                    done = true;
                    break;
                }
            }
            if(!done)response = "username not found";
        }
        done = false;
        return response;
    }
    public static String uploadedFile(){
        return Data.userFiles();
    }
    public static String signUpResponse(String[] data){
        for (MyUser u :
                Data.getINSTANCE().getUsers()) {
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
    public static String fileToDow(){
        File file = new File("C:\\Users\\EPSILON\\IdeaProjects\\tamrin5\\srever\\src\\doc");
        File[] files = file.listFiles();
        response = "";
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            response += files[i].getName();
            if(i == files.length - 1)break;
            response+= "/";
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
    public static void uploadResponse(String fileName){
        try{
          FileReceiver fileReceiver = new FileReceiver(new DatagramSocket(PORT));
          fileReceiver.path = "server";
          fileReceiver.fileName = fileName;
          fileReceiver.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
