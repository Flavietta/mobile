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
import com.codename1.components.SpanLabel;

import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.CharArrayReader;
import com.codename1.ui.Button;
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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.codename1.util.CallbackAdapter;
import com.mycompany.entity.Recommandation;
import com.mycompany.services.Authentication;
import com.mycompany.services.RecommandationController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


/**
 * The Login form
 *
 * @author Shai Almog
 */
public class AjoutRecommandation extends SideMenuBaseForm {

    String file = new String();

    
    public AjoutRecommandation(Resources theme) throws IOException {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite"),
                new Label("Conseil", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");
        Label labelFile = new Label();
        Image profilePic = theme.getImage("heart-plus-icon.png");

        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ShowRecommandation(theme).show();
        });
//        backbutton.addActionListener(e -> );

        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        profilePicLabel.getStyle().setOpacity(230);
        TextField titre = new TextField("", "", 20, TextField.ANY);
        titre.setHint("Titre");

        TextArea Contenu = new TextArea("", 5, TextField.CENTER);
        Contenu.setHint("Description");
        Contenu.getStyle().setFgColor(0xffffff);

//        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(""), new MultiButton("")));
        titre.getAllStyles().setMargin(LEFT, 0);

        Contenu.getStyle().setBgTransparency(5);
        Contenu.setHeight(40);

        Contenu.addPointerPressedListener((evt) -> {
            Contenu.getStyle().setBgTransparency(5);
        });
        Contenu.isGrowByContent();
      
     
       //Select File Button
       Button FileSelectButton = new Button("Select File");
       FileSelectButton.setUIID("LoginButton");
       FileSelectButton.addActionListener(e -> {
  
        if (FileChooser.isAvailable()) {
            Callback<String> resultURL=new CallbackAdapter<>();
        FileChooser.showOpenDialog(".pdf", e2-> {
            file = (String)e2.getSource();
            
        });
       }
    });

       
       //Add Button
        Button AjoutButton = new Button("Ajout");
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (titre.getText() != "" && Contenu.getText() != "") {
                try{
                Recommandation rec = new Recommandation();
                rec.setFile(titre.getText()+".pdf");
                rec.setDescription(Contenu.getText());
                rec.setTitle(titre.getText());
                rec.setOwner(Authentication.currentUser);
                RecommandationController.addRec(rec,file);
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Conseil est ajouté", "OK", null);
                }catch(Exception ex){
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Error", "Un erreur D'ajout ", "OK", null);
                    
                }
                new ShowRecommandation(theme).show();
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
//                
            }
        });

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                titre,
                Contenu,
                FileSelectButton,
                labelFile,
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
