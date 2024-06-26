package responseHandler;

import data.Data;
import model.MyUser;


public class LoginResponse extends Response{

    public LoginResponse(String[] data){
        if(data[1] == null ||data[2] == null){
            response = "enter username and passworld";
            done = true;
        }if(!done){
            for (MyUser u :
                    new Data().getUsers()) {
                if(data[1].equals(u.getUsername())){
                    if(data[2].equals(u.getPassword())){
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
    }
}
