package responses;


import model.MyUser;

public class handleResponse {
    public static void handleLoginResponse(String response, String username, String password){
        if(response.startsWith("access granted")){
            MyUser.currentUser = new MyUser(username, password);
        }
    }
    public static void handleSignUpResponse(String response, String username, String password){
        if(response.startsWith("access granted")){
            MyUser.currentUser = new MyUser(username, password);
        }
    }
}
