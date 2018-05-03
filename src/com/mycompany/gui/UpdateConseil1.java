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
import com.mycompany.gui.ListConseils;

/**
 * The Update Form
 *
 * @author Amine
 */
public class UpdateConseil1 extends SideMenuBaseForm {

    public UpdateConseil1(Resources theme, int c) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
     //   setUIID("LoginForm3");

        Container welcome = FlowLayout.encloseCenter(
                new Label("Update, ", "WelcomeWhite1"),
                new Label("Conseil", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("images.png");

        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
     //   menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ListConseils(theme).show();
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

//        TextField refc = new TextField("", "refc", 20, TextArea.NUMERIC);
        TextField titre = new TextField("", "", 20, TextField.ANY);

        TextField description = new TextField("", "", 20, TextField.ANY);

        TextArea Contenu = new TextArea("", 5, TextField.ANY);

        Contenu.getStyle().setFgColor(0x0000000);
        ComboBox<String> combo = new ComboBox<String>("Bon Conseil", "Conseil Bébé", "Conseil Homme", "Conseil Femme", "Conseil Voyage", "Conseil Alimentations");
        titre.getAllStyles().setMargin(LEFT, 0);
        description.getAllStyles().setMargin(LEFT, 0);

        Contenu.getStyle().setBgTransparency(5);
        Contenu.setHeight(40);

        Contenu.addPointerPressedListener((evt) -> {
            Contenu.getStyle().setBgTransparency(5);
        });
        Contenu.isGrowByContent();

        Button UpdateButton = new Button("Update");
       // UpdateButton.setUIID("LoginButton");
        ServiceConseil sv = new ServiceConseil();
        Conseil e = sv.AfficheDetails(c);
//        System.out.println("cccccccc : : : " + c);
        titre.setText(e.getTitre());
        description.setText(e.getDescription());
        Contenu.setText(e.getContenu());

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (titre.getText() != "" && description.getText() != "" && Contenu.getText() != "") {
                    Conseil up = new Conseil(c, titre.getText(), description.getText(), combo.getSelectedItem(), Contenu.getText());
//                    System.out.println(Contenu.getText());
                    com.mycompany.services.ServiceConseil update = new ServiceConseil();
                    update.udpateC(up);
//                    System.out.println("Update effectué");
                    ToastBar.showMessage("Votre Conseil a été modifié", FontImage.MATERIAL_INFO);
                    new ListConseils(theme).show();
                } else {
                    ToastBar.showMessage("Vous devez remplir tous le formulaire", FontImage.MATERIAL_INFO);
                }

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
//                profilePicLabel,
                spaceLabel,
                titre,
                description,
                Contenu,
                combo,
                UpdateButton
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
