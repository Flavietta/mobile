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

import com.mycompany.entity.Conseil;
import com.mycompany.services.ServiceConseil;
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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;


/**
 * The Ajout Form
 *
 * @author Amine
 */
public class AjoutConseil extends SideMenuBaseForm {

    public AjoutConseil(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
       // setUIID("LoginForm3");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite1"),
                new Label("Conseil", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("new.jpg");

        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
       // menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
       // menuButton.setUIID("WelcomeBlue1");
        
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {
         //    setUIID("WelcomeBlue1");
            new ListConseils(theme).show();
           
        });
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);
//
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Label profilePicLabel = new Label(profilePic, "ProfilePic");
//        profilePicLabel.setMask(mask.createMask());
//        profilePicLabel.getStyle().setOpacity(230);
        TextField titre = new TextField("", "", 20, TextField.ANY);
        titre.setHint("Titre");

        TextField description = new TextField("", "", 20, TextField.ANY);
        description.setHint("Description");

        TextArea Contenu = new TextArea("", 5, TextField.CENTER);
        Contenu.setHint("Contenu");
        Contenu.getStyle().setFgColor(0x000000);
        ComboBox<String> combo = new ComboBox<String>("Bon Conseil", "Conseil Bébé", "Conseil Homme", "Conseil Femme", "Conseil Voyage", "Conseil Alimentations");
       // combo.setUIID("ComboBox");
        titre.getAllStyles().setMargin(LEFT, 0);
        description.getAllStyles().setMargin(LEFT, 0);

        Contenu.getStyle().setBgTransparency(5);
        Contenu.setHeight(40);

        Contenu.addPointerPressedListener((evt) -> {
            Contenu.getStyle().setBgTransparency(5);
        });
        Contenu.isGrowByContent();
        Button AjoutButton = new Button("Ajouter");
       // AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (titre.getText() != "" && description.getText() != "" && Contenu.getText() != "") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Conseil est ajouté", "OK", null);
               
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
             }
        });
        AjoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if (titre.getText() != "" && description.getText() != "" && Contenu.getText() != "") {
                Conseil c = new Conseil(titre.getText(), description.getText(), combo.getSelectedItem(),Contenu.getText());
                com.mycompany.services.ServiceConseil sv = new ServiceConseil();
                sv.ajoutConseil(c);
                new ListConseils(theme).show();
            }
                 else {
                          ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
                 }
           
            }
            
        });

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
             //
                spaceLabel,
                titre,
                description,
                Contenu,
                combo,
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
