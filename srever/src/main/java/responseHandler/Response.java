package responseHandler;

import data.Data;
import model.MyUser;
import util.Hasher;

import static util.Util.createNewUser;

public class Response {
    static String response;
    static boolean done;
    public  static String LoginResponse(String[] data){
        if(data[1] == null ||data[2] == null){
            response = "enter username and passworld";
            done = true;
        }if(!done){
            for (MyUser u :
                    new Data().getUsers()) {
                if(data[1].equals(u.getUsername())){
                    if(data[2].equals(Hasher.hashPassword(u.getPassword()))){
                        response = "access granted";

                        done = true;
                        break;
                    }else {
                        response = "wrong password";
                        done = true;
                        break;
                    }
                }
            }
            if(!done)response = "username not found";
        }
        done = false;
        return response;
    }
    public static String signUpResponse(String[] data){
        for (MyUser u :
                new Data().getUsers()) {
            if(data[1].equals(u.getUsername())){
                response = "this username is taken";
                done = true;
                break;
            }
        }if(!done){
            response = "welcome";
            createNewUser(data[1], data[2]);
        }
        return response;
    }
}
