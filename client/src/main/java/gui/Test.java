package gui;

import model.MyUser;

import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        MyUser u = new MyUser();
        u.getFiles().add(new File("src/main/java/gui/testing.txt"));
        Menu menu = new Menu(u);
        String file = new Scanner(System.in).next();
        u.getFiles().add(new File("src/main/java/gui/" + file + ".txt"));
        System.out.println(u.getFiles());
    }
}
