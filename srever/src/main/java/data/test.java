package data;

import model.MyUser;

public class test {
    public static void main(String[] args) {
       Data data =  new Data();
       data.addUser(new MyUser("dfghi gbuikgb", "123423456"));
        System.out.println(data.getUsers());
    }
}
