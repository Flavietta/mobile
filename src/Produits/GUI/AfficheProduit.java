/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import Produits.Services.ServiceProduit;
import Produits.entities.Produits;
import com.codename1.components.ImageViewer;
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
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author yahya
 */
public class AfficheProduit extends SideMenuPBaseForm {

    public static int idProduit;
    public static String nomProduit;
    public static String etat;
    public static String description;
    public static String NomImageP;
    public static String UsernameP;
     public static String place ;

    private EncodedImage enc;

    private Label createForFont(Font fnt, Label s) {

        s.getUnselectedStyle().setFont(fnt);

        return s;
    }

    public AfficheProduit(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        setUIID("Toolbar");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Tous les ", "WelcomeWhite"),
                new Label("Produits", "WelcomeBlue")
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
       // titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSidePMenu(theme);

        ServiceProduit p = new ServiceProduit();
        List<Produits> lis = p.getList2();
        // System.out.println(p.getList2().toString());
        // lb.setText(lis.toString());

        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Produits t : lis) {

            Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            ImageViewer img2 = new ImageViewer();

            try {

                enc = EncodedImage.create("/giphy.gif");

                Dimension d = new Dimension(10, 10);

                Image i = (URLImage.createToStorage(enc, t.getNomImage(), "http://localhost/PIVPreFinal/web/iamges/" + t.getNomImage() + "", URLImage.RESIZE_SCALE));

                img2 = new ImageViewer(i.fill(100, 150));

                Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
                Label Nomp = new Label(t.getNomp());
                Nomp.getStyle().setFgColor(0xFF0000);

                Label Owner = new Label("Owner : " + t.getUsername());
                Label etatp = new Label("Etat : " + t.getEtat());
                Label Descp = new Label("Description : " + t.getDescription());

                Nomp.getStyle().setFgColor(0xFF0000);
                Owner.getStyle().setFgColor(0xFCFAE1);
                etatp.getStyle().setFgColor(0xFCFAE1);
                Descp.getStyle().setFgColor(0xFCFAE1);

             Random r = new Random();
int valeur = 91255566 + r.nextInt(99999999 - 91255566);

                Label teleph = new Label("numero telephone : "+Integer.toString(valeur) );
                C1.add(Nomp);

                C1.add(createForFont(smallPlainSystemFont, Owner));

                C1.add(createForFont(smallPlainSystemFont, etatp));
                C1.add(createForFont(smallPlainSystemFont, Descp));
                 C1.add(createForFont(smallPlainSystemFont, teleph));
                Slider a = new Slider();

                C2.add(img2);

                C2.add(C1);

                Button Jaime = new Button("");
                FontImage.setMaterialIcon(Jaime, FontImage.MATERIAL_FAVORITE);
              
                Button Map = new Button("");
                                FontImage.setMaterialIcon(Map, FontImage.MATERIAL_ROOM);

                
                Jaime.getStyle().setFgColor(0xFFFFFF);
                
                teleph.getStyle().setFgColor(0xFFFFFF);
                Map.getStyle().setFgColor(0xFFFFFF);
              
                C4.add(Jaime);
                C4.add(Map);
           

                C3.add(C2);
                C3.add(C4);
                C3.add(a);

               
                
                
                
                
                 ServiceProduit nrb = new ServiceProduit();
         
      
                  
   Jaime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
         idProduit = t.getId() ; 
     nomProduit = t.getNomp(); 
    etat = t.getEtat() ; 
     description =t.getDescription() ; 
     System.out.println(idProduit);  
     
     ServiceProduit a = new ServiceProduit();
     Produits prod = new Produits(t.getId(),t.getNomp(),t.getUsername(),t.getUserID());
     
     if (t.getUserID()==Login.useridPUB){ new Dialog().show("","vous ne pouvez pas ajouter votre produit au wishlist", "Ok", "Cancel");     }
     else{
     a.Panier(prod);
              new Dialog().show("","ajout au panier effectué", "Ok", "Cancel");   
           new AfficheProduit(theme).show();   
     }    }
        });
   
   
                
                
   
   
   
   
   
   
   Map.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                place= t.getVille();
     detail a = new detail();
      a.detail();
        }});
   
   
   
   
   
   
                
                
            } catch (IOException ex) {

            }

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
                C3,
                spaceLabel
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
