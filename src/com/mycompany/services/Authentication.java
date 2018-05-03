/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.mycompany.entity.User;
import com.mycompany.services.exception.LoginException;
import java.util.List;

/**
 *
 * @author FALAVIO
 */
public class Authentication {
    public static User currentUser=null;
    
    private Authentication(){
        
    }
    
    public static void login(String username , String password) throws LoginException{
        currentUser = null;
        List<User> currentUsers = UserController.getUsersByUsername(username);
        if (currentUsers.isEmpty()) {
            throw new LoginException("Username Doesn't exist");
        }
        for (User currentUser1 : currentUsers) {
            if (checkPassword(password, currentUser1)) 
                currentUser=currentUser1;
        }
        if (currentUser==null) 
            throw new LoginException("Incorrect password");
        
    }
    
    private static boolean checkPassword(String password , User user){
        
            String name = user.getUsernameCanonical();
            String pwd = user.getPassword();
            //if (pwd.contains("$2y$13$")) {
            
           // String pass=pwd.replace("$2y$13$", "$2a$13$");
            
        //}
            if (BCrypt.checkpw(password,pwd)) {
                currentUser=user;
                return true;
            }
                
        return false;
    }
    public static void logout(){
        if (currentUser!=null) {
            currentUser=null;
        }
    }
    
}
