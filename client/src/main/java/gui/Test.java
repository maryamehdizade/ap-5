package gui;

import model.MyUser;

public class Test {
    public static void main(String[] args) {
        MyUser.setINSTANCE(new MyUser("mmmm", "12345"));
        Menu menu = new Menu();

    }
}
