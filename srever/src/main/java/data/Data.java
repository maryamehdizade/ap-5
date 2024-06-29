package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import model.MyUser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            users = new ArrayList<>();
            users = objectMapper.readValue(file, new TypeReference<ArrayList<MyUser>>(){});
            for (MyUser u : users) {
                File f = new File("C:\\Users\\EPSILON\\IdeaProjects\\tamrin5\\srever\\src\\main\\java\\data\\userFiles\\" + u.getUsername());
                if(!f.exists())f.mkdir();
                File[] userFiles = f.listFiles();
                ArrayList<File> files = new ArrayList<>();
                files.addAll(List.of(userFiles));
                u.setFiles(files);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  ArrayList<MyUser> getUsers() {
        return users;
    }
}
