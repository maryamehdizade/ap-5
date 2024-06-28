package gui;

import model.MyUser;

public class Test {
    public static void main(String[] args) {
        MyUser.setINSTANCE(new MyUser("mary", "1234"));
        Menu menu = new Menu();
        menu.showFiles();
    }
}
