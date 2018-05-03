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

import com.mycompany.entity.Conseil;
import com.mycompany.entity.post;
import com.mycompany.services.Authentication;
import com.mycompany.services.ServiceConseil;
import com.codename1.components.SpanLabel;
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
import com.mycompany.gui.ListConseils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Login form
 *
 * @author Amine
 */
public class AfficheDetailsTry extends SideMenuBaseForm {

    public AfficheDetailsTry(Resources theme, int c) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       // tb.setUIID("Toolbar2");
        Image log = theme.getImage("CS.png");
        Label profilePicLabel = new Label("", log, "SideMenuTitle");
        Button menuButton = new Button("");
       // menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        setupSideMenu(theme);
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ListConseils(theme).show();
        });
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.CENTER, profilePicLabel)
                );
       // titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
//        setupSideMenu(theme);
        SpanLabel titre = new SpanLabel();

        SpanLabel description = new SpanLabel();
        SpanLabel Contenu = new SpanLabel();
        SpanLabel categorie = new SpanLabel();
        ServiceConseil sv = new ServiceConseil();
        Conseil e = sv.AfficheDetails(c);

        titre.setText("" + e.getTitre());
       // titre.setTextUIID("titleaff");
        titre.getStyle().setMarginLeft(65);
        description.setText(e.getDescription());
       // description.setTextUIID("textdesc");
        Contenu.setText(e.getContenu());
       // Contenu.setTextUIID("textdesc");
        categorie.setText("( " + e.getCategorie() + " )");
        //categorie.setTextUIID("cataff");
        categorie.getStyle().setMarginLeft(65);
        TextField smsnum = new TextField();
        smsnum.setHint("Numéro");
        Button btnSms = new Button("Envoyer SMS");
        FontImage.setMaterialIcon(btnSms, FontImage.MATERIAL_SMARTPHONE);
        btnSms.setUIID("LoginButton");
        btnSms.addActionListener(new ActionListener() {
                    String nom = Authentication.currentUser.getUsername();

            @Override
            public void actionPerformed(ActionEvent evt) {
                String myURL = "https://rest.nexmo.com/sms/json?api_key=50ff244f&api_secret=2LGFI6ZTqEzquNiq&to=" + "216" + smsnum.getText() + "&from=21658112495&text=" + "From Bien Etre Et Sante, "+nom+ "**Titre : " + e.getTitre() + " **Description : " + e.getDescription() + " **Categorie : " + e.getCategorie() + " **Contenu : " + e.getContenu();
                ConnectionRequest cntRqst = new ConnectionRequest() {

                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "Votre Message est envoyé avec Succès", "OK", null);
                        new AfficheDetailsTry(theme, c).show();
                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
          
            }
        });

        TextArea commme = new TextArea("", 4, TextField.CENTER);
        commme.setHint("Commenter Ce Conseil");

        commme.setHeight(40);

        commme.addPointerPressedListener((evt) -> {
            commme.getStyle().setBgTransparency(5);
        });
        commme.isGrowByContent();

        Button btnpost = new Button("Commenter");
        FontImage.setMaterialIcon(btnpost, FontImage.MATERIAL_COMMENT);
        btnpost.setUIID("LoginButton");

        btnpost.addActionListener(s -> {
            post p = new post(commme.getText(), e.getRefc(), "madame");
            sv.ajoutCommentaire(p);
            ToastBar.showMessage("Vous Commentaire est ajouté avec succés ", FontImage.MATERIAL_INFO);
            new AfficheDetailsTry(theme, c).show();
        });
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Button btnaffpost = new Button("Afficher Les Commentaires");
       // btnaffpost.setUIID("Titleupdatebtn1");
        FontImage.setMaterialIcon(btnaffpost, FontImage.MATERIAL_LIST);

        btnaffpost.addActionListener(s -> {

            CommentList aa = new CommentList(theme, c);
            new CommentList(theme, c).show();
//            aa.getF().show();

        });
        Label Desclab = new Label("Description :");
       // Desclab.setUIID("cataff1");
        Label contlab = new Label("Contenu :");
        //contlab.setUIID("cataff1");
        Label comm = new Label("Commentaires :");
       // comm.setUIID("cataff1");
        Button smsaff = new Button("You can send this advice to a friend");
        FontImage.setMaterialIcon(smsaff, FontImage.MATERIAL_SMARTPHONE);
       // smsaff.setUIID("cataff2");
        smsaff.addActionListener((evt) -> {
            ToastBar.showMessage("Insert Your number !! ", FontImage.MATERIAL_SMARTPHONE);

        });
        
        
        
        Button btnpartager = new Button("Partager Sur FB");
         btnpartager.setUIID("LoginButton");
        FontImage.setMaterialIcon(btnpartager, FontImage.MATERIAL_SHARE);

        btnpartager.addActionListener((evt) -> {
            
           String token = "EAACEdEose0cBACnYvd1qM5hZBCKlaSiKZBlnHi2eJmbP9U5NrdG07e5E4Fd7ZCeSu4wQe9e8QvWyKTcy3aWmmKyx7UKnD2BsuzHNwerkKATvoC6LwDS8Mq4nGz0HvJaDkG1q7eDKKZCgnA8qHNDGnw4uzZB2rWOyeSUJxhceX3Af4lIlQSg9M3eI26q6ezXRItAlWKG3I6wZDZD";
            FacebookClient fb = new DefaultFacebookClient(token);
            FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message","Titre Du Conseil : "+e.getTitre()+"\nDescription Du Conseil : "+e.getDescription()+"\n Catégorie : "+e.getCategorie()+"\n Contenu : "+e.getContenu() ));

        });
        Container by = BoxLayout.encloseY(
                spaceLabel,
                new Label(""),
                titre,
                categorie,
                new Label("       "),
                Desclab,
                description,
                contlab,
                Contenu,
                new Label(" "),
                comm,
                commme,
                btnpost,
                btnaffpost,
                new Label("     "),
                smsaff,
                btnpartager,
                smsnum,
                btnSms
        );
        add(BorderLayout.CENTER, by);
      //  by.setUIID("LoginForm55");

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
       
    }

    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
