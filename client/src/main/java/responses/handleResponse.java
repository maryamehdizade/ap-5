package responses;




public class handleResponse {
    public static boolean handleLoginResponse(String response, String username, String password) {
        return response.startsWith("access granted");
    }
    public static boolean handleSignUpResponse(String response, String username, String password){
        return response.startsWith("welcome");
    }
}
