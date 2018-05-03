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
public class NotEmailException extends Exception{

    private String message;
    public NotEmailException(String message) {
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
