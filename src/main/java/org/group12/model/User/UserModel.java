package org.group12.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    public  static List<User> users = new ArrayList<User>();
    public UserModel() {
        users.add(new User("planit","planit"));
    }


    public boolean checkCredentials(String userName,String password) {
        // check the username and password
        for(User user : users){
            return  user.getUserName().equals(userName) && user.getPassword().equals(password) ? true : false ;
        }
        return  false ;
    }
}