package model;

import java.io.File;
import java.util.ArrayList;

public class MyUser {
    private static MyUser INSTANCE;
    private String username;
    private String password;
    private ArrayList<File> files = new ArrayList<>();

    public MyUser() {
    }

    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public static MyUser getINSTANCE() {
        if(INSTANCE == null)INSTANCE = new MyUser();
        return INSTANCE;
    }

    public static void setINSTANCE(MyUser INSTANCE) {
        MyUser.INSTANCE = INSTANCE;
    }
}
