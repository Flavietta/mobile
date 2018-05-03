/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services.exception;

/**
 *
 * @author FALAVIO
 */
public class UserAlreadyExistsException extends Exception{
    private String message ; 
    private String userName;

    public UserAlreadyExistsException(String message, String userName) {
        this.message = message;
        this.userName = userName;
    }
    
    public String message(){
        return message;
    }
    
    public String userName(){
        return userName;
    }
}
