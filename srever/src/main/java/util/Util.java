package util;

import data.Data;
import model.MyUser;

public class Util {
    public static void createNewUser(String u, String p){
        MyUser newUser = new MyUser(u, Hasher.hashPassword(p));
        new Data().addUser(newUser);
    }
}
