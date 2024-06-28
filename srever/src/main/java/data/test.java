package data;

import model.MyUser;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File file = new File("server/src/doc");
        file.mkdir();
        System.out.println(file.exists());
        System.out.println(file.listFiles());
    }
}
