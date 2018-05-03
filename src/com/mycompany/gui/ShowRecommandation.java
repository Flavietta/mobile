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

import com.mycompany.entity.Recommandation;
import com.mycompany.services.RecommandationController;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Rate;
import com.mycompany.services.Authentication;
import com.mycompany.services.RateController;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class ShowRecommandation extends SideMenuBaseForm {
//
//    private Resources theme;
    public float so;
    Form f;
    SpanLabel lb;
  

    public ShowRecommandation(Resources theme) {

        
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
        tb.setUIID("Toolbar");
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
        Image log = theme.getImage("");
        Label profilePicLabel = new Label("", log, "Title");

        Button btn = new Button("");
        btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {
            try {
                new AjoutRecommandation(theme).show();
            } catch (IOException ex) {
            }
                });
        
        
/*
        Button btnupdate = new Button("");
        btnupdate.setUIID("Title");
        FontImage.setMaterialIcon(btnupdate, FontImage.MATERIAL_SPELLCHECK);
        btnupdate.addActionListener(e -> {

          

        });*/
//        Button btndelete = new Button("");
//        btndelete.setUIID("Title");
//        FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE_FOREVER);
//        btndelete.addActionListener(e -> {
//
//            new DeleteConseil(theme).show();
//
//        });

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
        //Service.ServiceConseil sr = new ServiceConseil();

        ArrayList<Recommandation> lis = (ArrayList<Recommandation>) RecommandationController.getAll();
        
        for (Recommandation R : lis) {

            Container c1 = new Container(BoxLayout.y());

            SpanLabel idRec = new SpanLabel("" + R.getId());
         
             
            SpanLabel titre = new SpanLabel("Titre : " + R.getTitle());
              
            SpanLabel description = new SpanLabel("Description : " + R.getDescription());
            SpanLabel owner = new SpanLabel("BY : " + R.getOwner().getUsername());
            owner.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
//            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
//            SpanLabel nomuser = new SpanLabel("Nom USer : " + R.getNomuser());
            Button btndel = new Button("");
            btndel.setUIID("Titlebtn");
            FontImage.setMaterialIcon(btndel, FontImage.MATERIAL_DELETE);
           
            //DELETE A Recommandation
            
             btndel.addActionListener(e -> {
                Recommandation c = new Recommandation();
                
                try { 
                    RecommandationController.deleteRec(R);
                    ToastBar.showMessage("Votre Recommandation est Supprimé avec Succès", FontImage.MATERIAL_INFO);  
                    new ShowRecommandation(theme).show();   
                }
                catch (Exception ex) {
                    Dialog.setDefaultBlurBackgroundRadius(8);
                    Dialog.show("Erreur", "Votre Recommandation n'est pas supprimé", "OK", null);
                }
           });
            
        Button btnupda = new Button("");
        btnupda.setUIID("Titleupdatebtn");
        FontImage.setMaterialIcon(btnupda, FontImage.MATERIAL_SPELLCHECK);
        //UpDATE
        btnupda.addActionListener(e -> {
              
                try {
                    
                    new UpdateRecommandation(theme,R).show();
                } catch (IOException ex) {
                    
                }
          

        });
        
        Button btnaffiche = new Button("");
        btnaffiche.setUIID("Titleaffichebtn");
        FontImage.setMaterialIcon(btnaffiche, FontImage.MATERIAL_VISIBILITY);
        btnaffiche.addActionListener(e -> {
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
        if(!fs.exists(fileName)) {
            Util.downloadUrlToFile("http://localhost/PIV1/web/"+R.getFile(), fileName, true);
        }
        Display.getInstance().execute(fileName);
            
        });
        //create the container of stars        
        Container stars = new Container();
        stars.setUIID("stars"+R.getId());
        
        //put stars in the container and add the listeners to each star
        setStars(stars, R);
        
            
            Container cont=new Container();
            if (Authentication.currentUser.equals(R.getOwner())) {
                cont = BoxLayout.encloseX(btndel,btnupda,btnaffiche);
            }else{
                cont = BoxLayout.encloseX(btnaffiche);
            }
            c1.add(stars).add(titre).add(description).add(owner).add(cont);
            
            c1.getStyle().setBorder(Border.createLineBorder(1, 0x1f1f14));
            ctn.add(c1);
            c1.getStyle().setBgTransparency(10);
            ctn.setScrollableY(focusScrolling);
           
            ctn.getStyle().setBgTransparency(10);

        }
        Style st = new Style();
        st.setBgColor(0);

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.center(
                        //                        

                        BoxLayout.encloseX(
                                spaceLabel,
                                new Label("Recommandations pour vous                   ", "CenterSubTitle"),
                                btn
//                                btnupdate,
//                                btndelete
                        )
                ).add(BorderLayout.NORTH, profilePicLabel)
        );

        ctn.setUIID("ctnTitle");
        
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
    
    public void setStars(Container stars,Recommandation R){
        stars.removeAll();
        System.out.println("Number of stars"+ (int)R.getRate());
        for (int i = 0; i < (int)R.getRate(); i++) {
            Button star = new Button();
            star.setUIID(i+"");
            FontImage.setMaterialIcon(star, FontImage.MATERIAL_STAR);
            stars.add(star);
        }for (int i = (int)R.getRate()+1; i < 6; i++) {
            Button star = new Button();
            star.setUIID(i-1+"");
            FontImage.setMaterialIcon(star, FontImage.MATERIAL_STAR_BORDER);
            stars.add(star);
        }
        //Add actions to the Stars
        setActionsToStars(stars, R);
    }
    
    public void setActionsToStars(Container stars , Recommandation R){
        for (Component star : stars) {
            System.out.println("THOSE ARE THE NUMBER OF MY STARS");
            ((Button)star).addActionListener(e->{
                System.out.println("The Star clicked is number "+Integer.valueOf(((Button)e.getSource()).getUIID())+1);
                System.out.println("THE STar is clicked");
                Rate rate = new Rate();
                rate.setRate(Integer.valueOf(((Button)e.getSource()).getUIID())+1);
                rate.setRec(R);
                rate.setVotedUser(Authentication.currentUser);
                RateController.addRate(rate,R);
                //new ShowRecommandation(theme).show();
                System.out.println("The Rating of this recommandation is " + R.getRate() + " and it has been rated " +R.getRateList().size() + " times");
                setStars(stars, R);
                
            });
        }
        
    }
    public void createRecommandationContainer(){
        
    }
    
}
