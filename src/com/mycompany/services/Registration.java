/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entity.User;
import com.mycompany.services.exception.NotEmailException;
import com.mycompany.services.exception.UserAlreadyExistsException;

/**
 *
 * @author FALAVIO
 */
public class Registration {
    
    public static void register(String email , String username , String password) throws NotEmailException, UserAlreadyExistsException{
        User user = new User();
       
        if (email.indexOf("@")<0) {
            throw new NotEmailException("Invalid Email Exception");
        }
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        if (UserController.all().contains(user)) {
            throw new UserAlreadyExistsException(username,"User Already Exists Exception");
        }
        UserController.addUser(user);
    }
    
}
