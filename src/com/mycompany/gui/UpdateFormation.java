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
import com.mycompany.gui.ListFormations;
import com.codename1.ui.spinner.Picker;
import java.util.Date;

/**
 * The Update Form
 *
 * @author Boj
 */
public class UpdateFormation extends SideMenuBaseForm {

    public UpdateFormation(Resources theme, int c) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
    //    setUIID("LoginForm3");

        Container welcome = FlowLayout.encloseCenter(
                new Label("Update ", "WelcomeWhite1"),
                new Label("Formation", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

      //  Image profilePic = theme.getImage("images.png");

      //  Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

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

//        TextField refc = new TextField("", "refc", 20, TextArea.NUMERIC);
 Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);

datePicker.setDate(new Date());
        TextField titre = new TextField("", "", 20, TextField.ANY);

        TextField domaine = new TextField("", "", 20, TextField.ANY);

        TextArea description = new TextArea("", 5, TextField.CENTER);
        TextField nombre = new TextField("", "", 20, TextField.ANY);
        domaine.getStyle().setFgColor(0x0000000);
        nombre.getStyle().setFgColor(0x0000000);
        titre.getAllStyles().setMargin(LEFT, 0);
        description.getAllStyles().setMargin(LEFT, 0);
         nombre.getAllStyles().setMargin(LEFT, 0);
        domaine.getStyle().setBgTransparency(5);
       domaine.setHeight(40);

        domaine.addPointerPressedListener((evt) -> {
            domaine.getStyle().setBgTransparency(5);
        });
        domaine.isGrowByContent();

        Button UpdateButton = new Button("Update");
        UpdateButton.setUIID("LoginButton");
        FormationService sv = new FormationService();
        //Formation e = sv.AfficheDetails(c);
        System.out.println("cccccccc : : : " + c);
       // titre.setText(e.getTitre());
       // description.setText(e.getDescription());
       // Contenu.setText(e.getContenu());

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (titre.getText() != "" && description.getText() != "" && domaine.getText() != "" && nombre.getText()!="") {
                    Formation up = new Formation(c, titre.getText(),datePicker.getText(), description.getText(), domaine.getText(),Integer.parseInt(nombre.getText()));
                   
                    FormationService update = new FormationService();
                    update.udpateF(up);
                    System.out.println("Update effectué");
                    ToastBar.showMessage("Votre Formation a été modifié", FontImage.MATERIAL_INFO);
                    new ListFormations(theme).show();
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
              //  profilePicLabel,
                spaceLabel,
                titre,
                datePicker,
                description,
                domaine,
                nombre,
                
                
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
