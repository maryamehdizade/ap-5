package util;

import data.Data;
import model.MyUser;

public class Util {
    public static void createNewUser(String u, String p){
        MyUser newUser = new MyUser(u, p);
        new Data().addUser(newUser);
    }
}
