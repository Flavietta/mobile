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
import com.mycompany.entity.Participation;
import com.mycompany.services.Authentication;
import com.mycompany.services.FormationService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class ListFormations extends SideMenuBaseForm {
//
//    private Resources theme;

    public float so;
    Form f;
    SpanLabel lb;
 private EncodedImage enc;
    public ListFormations(Resources theme) {

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
       // tb.setUIID("Toolbar1");
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
   //   Image log = theme.getImage("lili.png");
     //   Label profilePicLabel = new Label("", log, "Title");

        Button btn = new Button("");
        btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {

            new AjoutFormation(theme).show();

        });


        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent
                = BorderLayout.west(
                        BorderLayout.north(menuButton)
                );
        Button backBut = new Button("");
        //backBut.setUIID("Title");
        FontImage.setMaterialIcon(backBut, FontImage.MATERIAL_UNDO);
        backBut.addActionListener((evt) -> {
             Toolbar.setGlobalToolbar(false);
                       Toolbar.setGlobalToolbar(true);
                new AfficheVisitor(theme).show();
        });
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
        FormationService sr = new FormationService();

        ArrayList<Formation> lis = (ArrayList<Formation>) sr.getList2();
         ArrayList<Participation> listP = new ArrayList<>();
        String s = "*************************************************************************************************************";
        for (Formation R : lis) {

            Container c1 = new Container(BoxLayout.y());
             Button Participer =new Button("Participer");
            SpanLabel refc = new SpanLabel("" + R.getReferencef());

            String tit = "Titre : ";
            
            String descc = "Description : ";
             String date ="Date :";
            String domaine ="Domaine :";
           
            SpanLabel titre = new SpanLabel(tit + R.getTitre());
              SpanLabel date1 = new SpanLabel(date + R.getDate());
            SpanLabel description = new SpanLabel(descc + R.getDescription());
            SpanLabel domaine1 = new SpanLabel(domaine + R.getDomaine());
//            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
//            SpanLabel nomuser = new SpanLabel("Nom USer : " + R.getNomuser());
            


           ImageViewer img2 = new ImageViewer();
            try {

                enc = EncodedImage.create("/giphy.gif");

                Dimension d = new Dimension(10, 10);
                
                System.out.println("Nom Image : " + R.getNom_image());

                Image i = (URLImage.createToStorage(enc, R.getNom_image(), "http://localhost/PIVPreFinal/web/iamges/" + R.getNom_image() + "", URLImage.RESIZE_SCALE));

                 img2 = new ImageViewer(i.fill(150,250));

       
                

            } catch (IOException ex) {

            }

                
                    
        

   
            Button btndel = new Button("");
          //  btndel.setUIID("Titlebtn");
            int idr=R.getReferencef();
            FontImage.setMaterialIcon(btndel, FontImage.MATERIAL_DELETE);
            btndel.addActionListener(e -> {
                
                FormationService sv = new FormationService ();
                if (true) {
                    sv.DeleteF((float)idr);
                    new ListFormations(theme).show();
                    ToastBar.showMessage("Votre Formation est Supprimée avec Succès", FontImage.MATERIAL_INFO);
                } else {
                    Dialog.setDefaultBlurBackgroundRadius(8);
                    Dialog.show("Erreur", "Votre Formation n'est pas supprimée", "OK", null);
                }
            });
  int idd = Authentication.currentUser.getId();
            Button btnupda = new Button("");
        //    btnupda.setUIID("Titleupdatebtn");
            FontImage.setMaterialIcon(btnupda, FontImage.MATERIAL_SPELLCHECK);
            btnupda.addActionListener(e -> {
                
                new UpdateFormation(theme, idr).show();

            });
               int nbp=R.getNbp();
               int nbr=R.getNbr();
            FormationService sv = new FormationService ();
             /*listP=sv.rechPart(idd, idr);
             int length=listP.size();
          */
      Participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                if(nbr<nbp){
         if (true)
             {       sv.ajoutP(idd,idr);
                new ListFormations(theme).show();
             }
             else { Dialog.show("Erreur", "Vous etes inscrits deja", "OK", null);
             }
           }
            
            else { Dialog.show("Erreur", "Formation saturée", "OK", null);}
            }}); 
 
               String name = Authentication.currentUser.getUsername();
          
               Container cont;
               if(name.equals(R.getNomuser())){
                  cont = BoxLayout.encloseX(btndel, btnupda);
                
               }
               else{
                   cont = BoxLayout.encloseX(Participer);
                   
               }
          
          
            c1.add(titre).add(description).add(date1).add(img2).add(cont);

            c1.getStyle().setBorder(Border.createLineBorder(1, 0x1f1f14));
            ctn.add(c1);
            c1.getStyle().setBgTransparency(10);
            ctn.setScrollableY(focusScrolling);

            ctn.getStyle().setBgTransparency(10);

        }
        String e = "////////////////////////////////////////////////////////////////////////////////////////////////////";
        Style st = new Style();
        st.setBgColor(0);

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.center(
                        //                        

                        BoxLayout.encloseX(
                                spaceLabel,
                                new Label("Des Formations Pour Tous              Ajout", "CenterSubTitle"),
                                btn,backBut
                        //                                btnupdate,
                        //                                btndelete
                        )
                )
        );

   //     ctn.setUIID("ctnTitle1");

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
