/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import Produits.Services.ServiceProduit;
import Produits.entities.Produits;
import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.mycompany.gui.AfficheVisitor;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SideMenuBaseForm;
import java.io.IOException;


/**
 *
 * @author yahya
 */
public class UpdateProduit extends SideMenuBaseForm {

     private Image img;
    
  private String imgPath;
      
       String fileNameInServer;
    public UpdateProduit(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

       // setUIID("LoginForm2");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Update, ", "WelcomeWhite"),
                new Label("Produit", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new AfficheVisitor(theme).show();
        });


        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        //titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

       
        
        TextField nomp = new TextField(AfficheProduit.nomProduit, "", 20, TextField.ANY);
        
        nomp.getStyle().setFgColor(0x000000);
        
        TextField description = new TextField(AfficheProduit.description, "", 20, TextField.ANY);
      
        description.getStyle().setFgColor(0x000000);
      
        ComboBox<String> etat = new ComboBox<String>("Mauvais(e)", "Moyen(ne)", "Bon(ne)", "Excellent(e)");

//        etat.setRenderer(new GenericListCellRenderer<>(new MultiButton(""), new MultiButton("")));
        nomp.getAllStyles().setMargin(LEFT, 0);
        description.getAllStyles().setMargin(LEFT, 0);

       

        
          
        
        
        
        
        
        
        
     
        Button AjoutButton = new Button("Submit");
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (nomp.getText() != "" && description.getText() != "") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre produit est ajouté", "OK", null);
             Produits up = new Produits(AfficheProduit.idProduit ,nomp.getText(),description.getText(), etat.getSelectedItem()); 
                
            ServiceProduit update = new ServiceProduit();
               update.udpateP(up);
                new AfficheProduit(theme).show();
                
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
            
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
            
                spaceLabel,
                nomp,
                description,
             
                etat,
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
