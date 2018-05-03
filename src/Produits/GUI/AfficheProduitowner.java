/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import static Produits.GUI.AfficheProduit.NomImageP;
import static Produits.GUI.AfficheProduit.UsernameP;
import static Produits.GUI.AfficheProduit.description;
import static Produits.GUI.AfficheProduit.etat;
import static Produits.GUI.AfficheProduit.idProduit;
import static Produits.GUI.AfficheProduit.nomProduit;
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
import com.mycompany.gui.SideMenuBaseForm;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author yahya
 */

public class AfficheProduitowner extends SideMenuPBaseForm {

          private EncodedImage enc;
          private Label createForFont(Font fnt, Label s) {
        
        s.getUnselectedStyle().setFont(fnt);

        return s;
    }
    public AfficheProduitowner(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        setUIID("SideNavigationPanel");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Vos , ", "WelcomeWhite"),
                new Label("Produits", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new AfficheProduit(theme).show();
        });


        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSidePMenu(theme);

      
      

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         ServiceProduit p = new ServiceProduit();
        List<Produits> lis = p.getListowner();
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

                 img2 = new ImageViewer(i.fill(100,150));

                Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
                 Label Nomp = new Label(t.getNomp()) ;
                 Nomp.getStyle().setFgColor(0xFF0000);
                 
                                  Label Owner = new Label("Owner : "+t.getUsername()) ;
                                  Label etatp =  new Label("Etat : "+t.getEtat()) ;
                                  Label Descp =  new Label("Description : "+t.getDescription()) ;
                                  
                                  Nomp.getStyle().setFgColor(0xFF0000);
                                  Owner.getStyle().setFgColor(0xEFECCA );
                                  etatp.getStyle().setFgColor(0xEFECCA  );
                                  Descp.getStyle().setFgColor(0xEFECCA  );
                                  
                 
                C1.add(Nomp);
               
                 C1.add(createForFont(smallPlainSystemFont, Owner  ));
             
                   C1.add(createForFont(smallPlainSystemFont,etatp  ));
                   C1.add(createForFont(smallPlainSystemFont,Descp  ));
                 
                
                Slider a = new Slider();
   
                C2.add(img2);
               
                C2.add(C1);
   
   
   Button Delete = new Button("") ;
   Button Update = new Button("") ;
   FontImage.setMaterialIcon(Delete, FontImage.MATERIAL_DELETE);
       FontImage.setMaterialIcon(Update, FontImage.MATERIAL_TEXT_FORMAT);

   Update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
 idProduit = t.getId();
                        nomProduit = t.getNomp();

                        etat = t.getEtat();

                        description = t.getDescription();
                        NomImageP = t.getNomImage();
                        UsernameP = t.getUsername();
                        new UpdateProduit(theme).show();
                   
            }
        });
   
   
  Delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
  Produits a = new Produits(t.getId()) ;
                ServiceProduit delete = new ServiceProduit(); 
                delete.DeleteProduit(a);
                   new Dialog().show("","suppression effectué", "Ok", "Cancel");
               new AfficheProduitowner(theme).show();
            }
        });
   
   
   
   C4.add(Delete) ;
   C4.add(Update);
   
   
   
   
   
   
                C3.add(C2);
   C3.add(C4) ;
                C3.add(a);
                
                

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
