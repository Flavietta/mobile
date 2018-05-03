/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.exception.EmptyFieldException;
import com.mycompany.services.Authentication;
import com.mycompany.services.Registration;
import com.mycompany.services.UserController;
import com.mycompany.services.exception.LoginException;
import com.mycompany.entity.User;
import java.io.IOException;


/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
    
    String path = "";
    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        //tb.setUIID("Toolbar77");
        FileSystemStorage fs = FileSystemStorage.getInstance();
        FileSystemStorage.getInstance().delete(fs.getAppHomePath() + "pdf-sample.jpg");
        String fileName = fs.getAppHomePath() + "pdf-sample.jpg";
        if(!fs.exists(fileName)) {
           
            Util.downloadUrlToFile("http://localhost/PIV1/web/img/"+Authentication.currentUser.getImage(), fileName, true);
        } 
        Image profilePic = null;
        try {
            profilePic = Image.createImage(fileName);
        } catch (IOException ex) {
           
        }
       
        if (profilePic==null) {
        profilePic = res.getImage("213.png");
        }   
        Image mask = res.getImage("round-mask.png");
        
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        TextField login = new TextField(Authentication.currentUser.getUsername(), "Username", 20, TextField.EMAILADDR) ;
        
        TextField email = new TextField(Authentication.currentUser.getEmail(), "Email", 20, TextField.EMAILADDR) ;
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        
       /* Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("remaining tasks", "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("completed tasks", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");
*/
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(Authentication.currentUser.getUsername(), "Title")
                                    
                                )
                            ).add(BorderLayout.WEST, profilePicLabel)
                            //,
  //                      GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        login.getStyle().setFgColor(ColorUtil.WHITE);
        password.getStyle().setFgColor(ColorUtil.WHITE);
        email.getStyle().setFgColor(ColorUtil.WHITE);
        add(BorderLayout.center(login));
        add(BorderLayout.center(email));
        add(BorderLayout.center(password));
        getStyle().setBgImage(res.getImage("bbb.jpg"));
        
        Button loginButton = new Button("Modifier");
        loginButton.setUIID("LoginButton");
        login.addPointerPressedListener(e->{
            login.getStyle().setFgColor(ColorUtil.WHITE);
        if (login.getText().equals("Empty Username Field")) {
                login.setText("");
            }    
        });
        password.addPointerPressedListener(e->{
            password.getStyle().setFgColor(ColorUtil.WHITE);
        if (password.getText().equals("Empty Username Field")) {
                password.setText("");
            }    
        });
        
           
        Label loginIcon = new Label();
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_CAMERA_ALT, 3);
        
        loginIcon.addPointerPressedListener((ev) -> {
            path = Capture.capturePhoto();
            if(path == null) {
                showToast("User canceled Camera");
                return;
            }
        });
        add(loginIcon);
        
        
        loginButton.addActionListener(e -> {
                try {
                    if (login.getText().equals("")) 
                        throw new EmptyFieldException(login,"username couldn't be empty");
                    if (password.getText().equals("")) 
                        throw new EmptyFieldException(password,"password couldn't be empty");
                    User user = Authentication.currentUser;
                    user.setEmail(email.getText());
                    user.setUsername(login.getText());
                    user.setPassword(password.getText());
                    user.setImage(login.getText()+".jpg");
                    UserController.updateUser(user,path);
                    Authentication.currentUser = user;
                    Dialog.show("Modification avec succes", "", "OK", "Cancel");
                
                    Toolbar.setGlobalToolbar(false);
                    Toolbar.setGlobalToolbar(true);
                    new WalkthruForm(res).show();
                    
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
        
        add(loginButton);
        
        /*
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        */tb.setTitleComponent(titleCmp);
                        
        /*add(new Label("Today", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
        addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);
*/        
     
/*
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_PHOTO, 4, (ev) -> {
            Display.getInstance().openGallery(e -> {
                if(e == null || e.getSource() == null) {
                    showToast("User canceled Gallery");
                    return;
                }
                String filePath = (String)e.getSource();
                setImage(filePath, l);
            }, Display.GALLERY_IMAGE);
        });
  */      
        
        setupSideMenu(res);
    }
    
      
    private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (Exception ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
    }
    
    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
