/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import Produits.Services.ServiceProduit;
import Produits.entities.wishlist;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.AfficheVisitor;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SideMenuBaseForm;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author yahya
 */
 
public class wishlists extends SideMenuBaseForm {

    
  
    SpanLabel wish ; 

    
    
    
    
    public wishlists(Resources theme) {
        
        
        
        super(new BorderLayout());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Wish ", "WelcomeWhite"),
                new Label("List", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

       
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
       

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new AfficheProduit(theme).show();
        });
//        backbutton.addActionListener(e -> );

        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        //titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

        
      
     
        
        ///////////////////
        
        
      
        
         Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
          ServiceProduit p = new ServiceProduit();
        List<Produits.entities.wishlist> lis = p.wishlist();
        
                 for (Produits.entities.wishlist t : lis) {
                     System.out.println(lis);
                     Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     System.out.println(t.getNomproduit() ); 
 wish = new SpanLabel("vous avez demandé le produit ( "+t.getNomproduit()+" ) de l utilisateur ( "+t.getOwner()+")") ; 
      //wish.setTextUIID("TodayEntry");
      wish.setPreferredH(100);
    
     Button supp = new Button("              supprimer               ") ; 
        
         C3.add(wish);
         C3.add(supp);
         C4.add(C3);
         
         
         
         supp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
wishlist a = new  wishlist( t.getPanier());
                System.out.println(a);
                     ServiceProduit b = new ServiceProduit() ;
                     b.DeletePanier(a);
                     new AfficheProduit(theme).show();
                   
            }
        });       
        }

         
               
            
        
        
        
        
        
        
        
        
        
        
        
        
        

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
         
           C4,
                spaceLabel
            
        );
    add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

    public wishlists() {
      
    }

    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
