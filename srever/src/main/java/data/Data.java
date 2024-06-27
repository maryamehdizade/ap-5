package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import model.MyUser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
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
    public void userFiles(MyUser user) {
        for (MyUser u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                user.setFiles(u.getFiles());
                break;
            }
        }

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
