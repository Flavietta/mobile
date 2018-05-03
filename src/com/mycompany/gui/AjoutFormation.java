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

import com.mycompany.entity.Formation;
import com.mycompany.services.FormationService;
import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;


/**
 * The Ajout Form
 *
 * 
 */
public class AjoutFormation extends SideMenuBaseForm {

    private Image img;
    
  private String nom_image;
      
       String fileNameInServer; 
    public AjoutFormation(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
    //    setUIID("LoginForm3");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite1"),
                new Label("Formation", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

//        Image profilePic = theme.getImage("new.jpg");
//
//        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
      //  menuButton.setUIID("WelcomeBlue1");
        
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {
           //  setUIID("WelcomeBlue1");
            new ListFormations(theme).show();
           
        });
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Label profilePicLabel = new Label(profilePic, "ProfilePic");
//        profilePicLabel.setMask(mask.createMask());
//        profilePicLabel.getStyle().setOpacity(230);
        TextField titre = new TextField("", "", 20, TextField.ANY);
        titre.setHint("Titre");
Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);

datePicker.setDate(new Date());
        TextField domaine = new TextField("", "", 20, TextField.ANY);
        domaine.setHint("Domaine");

        TextArea Description = new TextArea("", 5, TextField.CENTER);
        Description.setHint("Description");
        TextField nombre = new TextField("", "", 20, TextField.ANY);
        nombre.setHint("Nbre");
        Description.getStyle().setFgColor(0x000000);
       
        titre.getAllStyles().setMargin(LEFT, 0);
        domaine.getAllStyles().setMargin(LEFT, 0);
        Button btnAjoutImage=new Button("btnAjoutImage");
        btnAjoutImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     try {
                        nom_image= Capture.capturePhoto();
                        img = Image.createImage(nom_image);    
                       
                         System.out.println("7aa");
                        System.out.println(nom_image);
                        String link = nom_image.toString();
                        int pod= link.indexOf("/", 2);
                        String news = link.substring(pod+2, link.length());
                        System.out.println(""+news);
                     
        FileUploader fu = new FileUploader("http://localhost/PIVPreFinal/web/");
        
        //Upload
        fileNameInServer = fu.upload(news);
                        System.out.println("-----------"+fileNameInServer+"---------------");
        
       
                     
                    } catch (IOException ex) {
                          ex.printStackTrace();
                    } catch (Exception ex) {
                    }
           }
            });
       
        
        
        
        
       nombre.getStyle().setFgColor(0x000000);
       
        nombre.getAllStyles().setMargin(LEFT, 0);
        nombre.getAllStyles().setMargin(LEFT, 0);
         Button btnAjout;
        
   
          
       
        Button AjoutButton = new Button("Ajouter");
        
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (titre.getText() != ""  && datePicker.getText()!="" && Description.getText() != "" && domaine.getText() != "" && nombre.getText() !="") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Formation est ajoutée avec succés", "OK", null);
               
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
             }
        });
         FormationService sv = new FormationService ();
        AjoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if (titre.getText() != "" && Description.getText() != "" && domaine.getText() != "" && nombre.getText() !="") {
                     int f=Integer.parseInt(nombre.getText());
                     Formation c = new Formation(titre.getText(),datePicker.getText(),domaine.getText(),Description.getText(),fileNameInServer,f);
                     System.out.println(nombre.getText());
                     System.out.println(c);
               
                     sv.ajoutF(c);
                new ListFormations(theme).show();
            }
                 else {
                          ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
                 }
           
            }
            
        });
        AjoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
Message m = new Message("Body of message");

Display.getInstance().sendMessage(new String[] {"ryhem.naouar@esprit.tn"}, "Subject of message", m);}
        });
  
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
               // profilePicLabel,
                spaceLabel,
                titre,
                Description,
                domaine,
                datePicker,
                nombre,
                btnAjoutImage,
               
               
                AjoutButton
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
       
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
    
    
    
   
    
}
