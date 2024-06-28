package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import model.MyUser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
private static Data INSTANCE;

    public static Data getINSTANCE() {
        if(INSTANCE == null)INSTANCE = new Data();
        return INSTANCE;
    }

    private ObjectMapper objectMapper = new ObjectMapper();
    private File file = new File("srever/src/main/java/data/user.json");

    private ArrayList<MyUser> users = new ArrayList<>();

    public Data(){
        deserialize();
    }
    public void addUser(MyUser user){
        users.add(user);
        serialize();
    }
    public static String userFiles() {
        StringBuilder stringBuilder = new StringBuilder();
        for (File u : MyUser.getINSTANCE().getFiles()) {
           stringBuilder.append(u.getName());
           if(u.equals(MyUser.getINSTANCE().getFiles().get(MyUser.getINSTANCE().getFiles().size() - 1)))break;
           stringBuilder.append("/");
        }

        return stringBuilder.toString();
    }
    public void addFile(MyUser user, File file){
        for (MyUser u: users) {
            if (u.getUsername().equals(user.getUsername())){
                u.getFiles().add(file);
                //todo:chage this
                serialize();
            }
        }
    }
    public void serializeFile(MyUser user){
        for (MyUser u: users) {
            if (u.getUsername().equals(user.getUsername())) {

            }
        }
    }
    private void serialize(){
        try {
            objectMapper.writeValue(file, null);
            objectMapper.writeValue(file,users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deserialize(){
        try {
            users = objectMapper.readValue(file, new TypeReference<ArrayList<MyUser>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  ArrayList<MyUser> getUsers() {
        return users;
    }
}
