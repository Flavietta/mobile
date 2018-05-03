/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.exception;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.TextField;

/**
 *
 * @author FALAVIO
 */
public class EmptyFieldException extends Exception{

    public EmptyFieldException(TextField textField) {

    }
    public EmptyFieldException(TextField textField,String message) {
        textField.setText(message);
    }
    
}
