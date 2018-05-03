/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.exception.EmptyFieldException;
import com.mycompany.services.Authentication;
import com.mycompany.services.RecommandationController;
import com.mycompany.services.UserController;
import com.mycompany.services.exception.LoginException;
import com.mycompany.entity.User;

/**
 *
 * @author FALAVIO
 */
public class ChangePassword  extends Form {
    public ChangePassword(Resources theme,User user) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("New Password, ", "WelcomeWhite")//,
               // new Label("Jennifer", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        //Image profilePic = theme.getImage("user-picture.jpg");
        //Image mask = theme.getImage("round-mask.png");
        /*profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        */
        TextField login = new TextField("", "Password", 20, TextField.EMAILADDR) ;
        login.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_CONTACT_PHONE, 3);
        
        Button loginButton = new Button("Confirm");
        loginButton.setUIID("LoginButton");
        login.addPointerPressedListener(e->{
            login.getStyle().setFgColor(ColorUtil.WHITE);
        if (login.getText().equals("Empty Username Field")) {
                login.setText("");
            }    
        });
        
        loginButton.addActionListener(e -> {
                try {
                    if (login.getText().equals("")) 
                        throw new EmptyFieldException(login,"username couldn't be empty");
                    user.setPassword(login.getText());
                    UserController.updateUser(user);
                    
                    Toolbar.setGlobalToolbar(false);
                    new LoginForm(theme).show();
                    Toolbar.setGlobalToolbar(true);
                    //Udate Static test
                    /*
                    User user = new User();
                    user.setEmail("Samir@gmail.com");
                    user.setUsername("Samirov");
                    user.setPassword("samirov");
                    UserController.updateUser(user);
                    /*
                    
                    */

                } catch (EmptyFieldException ex) {
                    
                }   
             
        });
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        Container by = BoxLayout.encloseY(
                welcome,
              //  profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                loginButton
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
