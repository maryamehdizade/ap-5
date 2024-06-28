package util;

import data.Data;
import model.MyUser;

import java.io.File;

public class Util {
    public static MyUser createNewUser(String u, String p){
        MyUser newUser = new MyUser(u, Hasher.hashPassword(p));
        Data.getINSTANCE().addUser(newUser);
        return newUser;
    }
    public static File getFile(String fileName){
        File file = new File("src/doc");
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.getName().equals(fileName))return f;
        }
        return null;
    }
}
