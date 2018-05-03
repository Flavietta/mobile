/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entity.Soins;
import com.mycompany.entity.Symptomes;
import com.mycompany.services.Authentication;
import com.mycompany.services.ServicesSoins;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.Map;

/**
 *
 * @author majid
 */
public class Details extends SideMenuBaseForm {
    Form f;
     public Details(Resources theme,Soins S,int indice) {
        super(new BorderLayout());
        f=new Form();
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
       // setUIID("DetailsForm");

        Container welcome = FlowLayout.encloseCenter(
                new Label(" Case ", "WelcomeWhite"),
                new Label("Details", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {
            if(indice==1){
            new afficheSoins(theme).show();
                }
                else{
            new AfficheNotif(theme).show();
            }
        });
        
            
             Container boku = new Container(BoxLayout.y());
             Container c1 = new Container(BoxLayout.y());
             Container sys=new Container(BoxLayout.y());
            SpanLabel refs = new SpanLabel("" + S.getRefs());
            
            SpanLabel titre = new SpanLabel("Titre:" + S.getTitre());
            SpanLabel description = new SpanLabel("Description :\n" + S.getDescription());
            
            description.getStyle().setFgColor(0xffffff);
//            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
           SpanLabel nomuser = new SpanLabel("Posted By : " + S.getUserName());
           if(Authentication.currentUser.getUsername().equals(S.getUserName()))
              nomuser.setText("Posted By : me ");
          
           //nomuser.setUIID("Posted");
            int jj =1;
            for(Symptomes dd:S.getSympsoms()){
                SpanLabel sym=new SpanLabel("symptome "+jj+" : "+dd.getSymptome());
                sys.add(sym);
                jj++;
            }
//            Image like = theme.getImage("likeon2.jpg");
//            Image dislike=theme.getImage("dislikeon2.jpg");
//        Image mask = theme.getImage("round-mask.png");
////        mask=mask.fill(30, 30);
//        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        like=like.fill(40, 40);
//        dislike=dislike.fill(40, 40);
        ServicesSoins ss=new ServicesSoins();
         
        if(ss.checkLike(S.getRefs(),Authentication.currentUser.getId())){
//        like = theme.getImage("likeon2.jpg");
        }
        else{
//        like = theme.getImage("likeoff.png");
        }
        //////////////////////
        if(ss.checkDislike(S.getRefs(),Authentication.currentUser.getId())){
//        dislike = theme.getImage("dislikeon2.jpg");
        }
        else{
//        dislike = theme.getImage("dislikeoff.png");
        }
        ////////////////////////
       
        Button Dislike=new Button("");
        Button Like=new Button("");
        
        FontImage.setMaterialIcon(Like, FontImage.MATERIAL_ACCOUNT_CIRCLE);
        FontImage.setMaterialIcon(Dislike, FontImage.MATERIAL_ACCOUNT_CIRCLE);
        
//Label Like = new Label(like);
//        Like.setMask(mask.createMask());
        Like.addActionListener((evt) -> {
        if(ss.checkLike(S.getRefs(),Authentication.currentUser.getId())){
            ss.likeOff(S.getRefs(),Authentication.currentUser.getId());
            
        }
        else{
            ss.likeOn(S.getRefs(), Authentication.currentUser.getId());
        
        }
        new Details(theme, S,indice).show();
        
        });
        
        ///////////
        Dislike.addActionListener((evt) -> {
           if(ss.checkDislike(S.getRefs(),Authentication.currentUser.getId())){
            ss.disLikeOff(S.getRefs(),Authentication.currentUser.getId());
        }
        else{
            ss.disLikeOn(S.getRefs(), Authentication.currentUser.getId());
        }
        new Details(theme, S,indice).show();
        });
        /////////////
//        Dislike.setMask(mask.createMask());
        Label nbLike=new Label(ss.getNbLike(S.getRefs())+" Like");
        nbLike.addPointerPressedListener((evt) -> {
            String users="";
            if(ss.getNbLike(S.getRefs())!=0){
            for(Map<String,String> m:ss.getUsersLike(S.getRefs())){
                if(m.get("name").equals(Authentication.currentUser.getUsername())){
                    users=users+"You"+"\n";
                }else{
                users=users+m.get("name")+"\n";
                }
                
            }
                
          Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("people who like this" ,users, "OK", null);
        
            
        }else{Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("no one","no", "OK", null);
                }});
        
       // nbLike.setUIID("like");
        Label nbDislike=new Label(ss.getNbDislike(S.getRefs())+" Dislike");
       // nbDislike.setUIID("like");
        nbDislike.addPointerPressedListener((evt) -> {
            String users="";
            if(ss.getNbDislike(S.getRefs())!=0){
                
            for(Map<String,String> m:ss.getUsersDislike(S.getRefs())){
                if(m.get("name").equals(Authentication.currentUser.getUsername())){
                    users=users+"You"+"\n";
                }else{
                users=users+m.get("name")+"\n";
                }
            }
          Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("people who doesn t like this" ,users, "OK", null);
        
            
        }else{Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("no one","no", "OK", null);
                }});
        Container gauche=BoxLayout.encloseY(Like,nbLike);
        gauche.setPreferredW(150);
        Container droit=BoxLayout.encloseY(Dislike,nbDislike);
        droit.setPreferredW(150);
        Container thumbs=BoxLayout.encloseX(
                gauche,
                droit
        );
            
            
            
            c1.add(nomuser).add(titre).add(description);
            boku.add(c1).add(sys);
            Container all =BoxLayout.encloseY(
                    welcome,
                    boku,
                    thumbs
            ); 
        
         add(BorderLayout.CENTER, all);
//      

     }
    
    
    
    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
}
