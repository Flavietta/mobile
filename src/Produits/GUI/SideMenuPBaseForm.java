/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import com.mycompany.services.Authentication;
import Produits.Services.ServiceProduit;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.LoginForm;


/**
 *
 * @author yahya
 */
public abstract class SideMenuPBaseForm extends Form {

    public SideMenuPBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuPBaseForm(String title) {
        super(title);
    }

    public SideMenuPBaseForm() {
    }

    public SideMenuPBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSidePMenu(Resources res) {
//        Image profilePic = res.getImage("aa.jpg");
//        Image mask = res.getImage("round-mask.png");
//        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Image log = res.getImage("logo2.png");
      //  Label profilePicLabel = new Label("", log, "SideMenuTitle");
      //  profilePicLabel.setHeight(TOP);
//        profilePicLabel.setMask(mask.createMask());
//        profilePic.animate();
        
        
//
//        Container sidemenuTop = BorderLayout.center("");
//        sidemenuTop.setUIID("SidemenuTop");
        
        ServiceProduit nrb = new ServiceProduit();
           ServiceProduit nrb1 = new ServiceProduit();
       
//        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Ajout ", FontImage.MATERIAL_PREGNANT_WOMAN,  e -> new AjoutProduit(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Gestion", FontImage.MATERIAL_SPA,  e -> new AfficheProduitowner(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Wishlist    ", FontImage.MATERIAL_SHOPPING_BASKET,  e -> new wishlists(res).show());
        getToolbar().addMaterialCommandToSideMenu("  notification  " , FontImage.MATERIAL_STAR,  e -> new Notif(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout  ", FontImage.MATERIAL_OFFLINE_PIN,  e -> {Authentication.logout(); new LoginForm(res).show();});

    }
    
   public abstract void showOtherForm(Resources res);
}
