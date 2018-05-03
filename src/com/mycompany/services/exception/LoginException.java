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
public class LoginException extends Exception{
    private String msg=null;
    
    
    public LoginException(String message){
        this.msg = message;
    }
    
    public String getMessage(){
        return msg;
    }
}
