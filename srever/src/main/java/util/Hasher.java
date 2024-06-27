package util;

import java.security.MessageDigest;

public class Hasher {
    public static String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder h = new StringBuilder();
            for (byte b: hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1){
                    h.append('0');
                }
                h.append(hex);
            }
            return h.toString();
        }catch (Exception r){
            r.printStackTrace();
            return null;
        }
    }
}
