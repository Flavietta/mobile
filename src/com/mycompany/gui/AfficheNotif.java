/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entity.Notif_soin;
import com.mycompany.entity.Soins;
import com.mycompany.services.ServicesNotif;
import com.mycompany.services.ServicesSoins;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author majid
 */
public class AfficheNotif extends SideMenuBaseForm {
       Form f;
    SpanLabel lb;
    public float so;

    public AfficheNotif(Resources theme) {

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
       // tb.setUIID("Toolbar1");
//        Image bgImg = theme.getImage("original.jpg");
        //tb.getStyle().setBgImage(bgImg);
      ///  tb.setHeight(400);
       // tb.setPreferredH(120);
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
         tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {
            new afficheSoins(theme).show();
            
        });
        
        
        //Image log = theme.getImage("23.png");
        //Label profilePicLabel = new Label("", log, "Title");
        ServicesNotif n=new ServicesNotif();
        System.out.println("notifff     \n "+n.getNotifs());
        System.out.println("////////////////////");
        
        /*Button btn = new Button("");
        btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {

            new AjoutSoins(theme).show();

        });
        */

      /*  Button btnupdate = new Button("");
        btnupdate.setUIID("Title");
        FontImage.setMaterialIcon(btnupdate, FontImage.MATERIAL_SPELLCHECK);
        btnupdate.addActionListener(e -> {

            //new UpdateSoin(theme).show();

        });
        Button btndelete = new Button("");
        btndelete.setUIID("Title");
        FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE_FOREVER);
        btndelete.addActionListener(e -> {

            //new DeleteConseil(theme).show();

        });*/

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent
                = BorderLayout.west(
                        BorderLayout.north(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        lb = new SpanLabel("");
        Container ctn = new Container(BoxLayout.y());
        ctn.setScrollableY(true);

        f.add(lb);
        ServicesNotif sr = new ServicesNotif();
        ServicesSoins sb =new ServicesSoins();
        ArrayList<Notif_soin> lis = (ArrayList<Notif_soin>) sr.getNotifs();
       
        
        //Image mask = theme.getImage("round-mask.png");
        //mask.fill(20, 20);
        
        
    
        
        for (Notif_soin N : lis) {

            Container c1 = new Container(BoxLayout.y());
             Container sys=new Container(BoxLayout.y());
            SpanLabel usersender = new SpanLabel("Dr. " +N.getNom_user_sender()+"  a ajouté un nouveau Soin qui peut vous interesser");
            
              
          //Label nomuser = new Label("" + N.getNom_user_sender()+" a ajouté un nouveau Soin qui peut vous interesser");
          
          //nomuser.setUIID("Posted");
           
//       FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE_FOREVER);
        
           
        Soins S =sb.A3tiniSoins(N.getId_soins());
        Label lx =new Label("view details");
       // lx.setUIID("viewdetails");
            lx.addPointerPressedListener((evt) -> {
            //     Dialog.setDefaultBlurBackgroundRadius(8);
              //  Dialog.show("Erreur", "Votre S n'est pas supprimé", "OK", null);
            
              new Details(theme ,S,2).show();
              sr.deleteNotif(N.getId_soins(),N.getId_user_rec());
                System.out.println("get the  out");
            });

              Container cont = BoxLayout.encloseX(lx);
            
              
            c1.add(usersender).add(cont);
            c1.setScrollableY(focusScrolling);
            c1.getStyle().setBorder(Border.createLineBorder(1, 0x1f1f14));
             
            ctn.add(c1);
            c1.getStyle().setBgTransparency(10);
            ctn.setScrollableY(focusScrolling);
            ctn.getScrollable();
            ctn.getStyle().setBgTransparency(10);
            
        }
        Style st = new Style();
        st.setBgColor(0);

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.center(
                        //                        

                        BoxLayout.encloseX(
                                spaceLabel,
                                new Label("Mes Notification                                  ", "CenterSubTitle")
                                
                               /* btnupdate,
                                btndelete*/
                        )
                )//.add(BorderLayout.NORTH, profilePicLabel)
        );

        add(ctn);

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        tb.setTitleComponent(titleCmp);
      
//        fab.setVisible(false);
    }

    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
