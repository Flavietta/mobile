/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.services.FormationService;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author user
 */
public class DeleteFormation {
 
   
/**
 * The Login form
 *
 * @author Shai Almog
 */
public class DeleteConseil extends SideMenuBaseForm {


    public DeleteConseil(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Delete ", "WelcomeWhite"),
                new Label("Formation", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

//        Image profilePic = theme.getImage("trash_can-512.png");
//
//        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ListFormations(theme).show();
        });
//        backbutton.addActionListener(e -> );

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
        TextField refc = new TextField("");
        refc.setHint("Référence");

        refc.getAllStyles().setMargin(LEFT, 0);

        Button DeleteButton = new Button("Effacer");
        DeleteButton.setUIID("LoginButton");
        DeleteButton.addActionListener(e -> {

            if (refc.getText() != "") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Formation est Supprimée", "OK", null);

              

            } else {

                ToastBar.showMessage("Vous devez ajouter la Référence", FontImage.MATERIAL_INFO);
//                
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if (refc.getText() != "") {
                FormationService sv = new FormationService();
                sv.DeleteF(Float.valueOf(refc.getText()));
                  new ListFormations(theme).show();
            }
                  else {
                          ToastBar.showMessage("Vous devez ajouter la Référence", FontImage.MATERIAL_INFO);
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
                refc,
                DeleteButton
                      
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
        
    }
    @Override
    public void showOtherForm(Resources res) {
    }
                 }

}
