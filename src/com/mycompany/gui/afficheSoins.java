
package com.mycompany.gui;

import com.mycompany.entity.Soins;
import com.mycompany.entity.Symptomes;
import com.mycompany.services.Authentication;
import com.mycompany.services.ServicesNotif;
import com.mycompany.services.ServicesSoins;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class afficheSoins extends SideMenuBaseForm {
//
//    private Resources theme;

    Form f;
    SpanLabel lb;
    public float so;

    public afficheSoins(Resources theme) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
       // tb.setUIID("Toolbar1");
       // Image bgImg = theme.getImage("original.jpg");
       // tb.getStyle().setBgImage(bgImg);
       
       tb.setHeight(400);
        tb.setPreferredH(120);
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
        //Image log = theme.getImage("23.png");
        //Label profilePicLabel = new Label("", log, "Title");
        ServicesNotif n=new ServicesNotif();
        System.out.println("notifff     \n "+n.getNotifs());
        
        
        Button btn = new Button("");
        btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {

            new AjoutSoins(theme).show();

        });

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
        ServicesNotif sn =new ServicesNotif();
        ServicesSoins sr = new ServicesSoins();

        ArrayList<Soins> lis = (ArrayList<Soins>) sr.getSoins();
        
        Label nbnt =new Label();
        nbnt.getStyle().setFgColor(0xB22222);
        
        Button key = new Button("");
        key.setUIID("Title");
        FontImage.setMaterialIcon(key, FontImage.MATERIAL_ADD_ALERT);
        
        key.addActionListener((evt) -> {
             new AfficheNotif(theme).show();
        
        });
        nbnt.setText(""+sn.countNbNotif());
  
        for (Soins S : lis) {

            Container c1 = new Container(BoxLayout.y());
             Container sys=new Container(BoxLayout.y());
            SpanLabel refs = new SpanLabel("" + S.getRefs());
            
            SpanLabel titre = new SpanLabel("Case : " + S.getTitre());
            SpanLabel description = new SpanLabel("Description : " + S.getDescription());
//            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
          Label nomuser = new Label("Posted By : " + S.getUserName());
          if(Authentication.currentUser.getUsername().equals(S.getUserName()))
              nomuser.setText("Posted By : me ");
          
        //  nomuser.setUIID("Posted");
            for(Symptomes dd:S.getSympsoms()){
                SpanLabel sym=new SpanLabel("symp: "+dd.getSymptome());
                sys.add(sym);
            }
            Button btndel = new Button("");
        //    btndel.setUIID("button55");
            FontImage.setMaterialIcon(btndel, FontImage.MATERIAL_DELETE);
  
//       FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE_FOREVER);
            btndel.addActionListener(e -> {
                
                ServicesSoins sv = new ServicesSoins();
                float foo = Float.valueOf(refs.getText());
              if (true){
                      sv.deleteSoins((int)foo);
                        new afficheSoins(theme).show();
                         ToastBar.showMessage("Votre Soin est Supprimé avec Succès", FontImage.MATERIAL_INFO);
              }
              else{
                  Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Erreur", "Votre S n'est pas supprimé", "OK", null);
              }
              
              
              

            });
            Button btnupda = new Button("");
       // btnupda.setUIID("button66");
        btnupda.getStyle().setBgColor(0xF1F1F4);
        FontImage.setMaterialIcon(btnupda, FontImage.MATERIAL_SPELLCHECK);
        btnupda.addActionListener(e -> {
              so = Float.valueOf(refs.getText());
              System.out.println(refs.getText());
            new UpdateSoin(theme,(int)so).show();
          

        });
        btnupda.getStyle().setBgColor(0xFFFFFF);
        Label lx =new Label("view details");
       // lx.setUIID("viewdetails");
            lx.addPointerPressedListener((evt) -> {
            //     Dialog.setDefaultBlurBackgroundRadius(8);
              //  Dialog.show("Erreur", "Votre S n'est pas supprimé", "OK", null);
            new Details(theme ,S,1).show();
                System.out.println("get the  out");
            });
              int idd = Authentication.currentUser.getId();
              Container cont;
              if(idd==S.getUserID()){
             cont = BoxLayout.encloseX(btndel,btnupda,lx);
                  
              }
              else{
                   cont = BoxLayout.encloseX(lx);
            
              }
            c1.add(nomuser).add(titre)./*add(description).add(sys).*/add(cont);
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
                                new Label("                    Ajouter", "CenterSubTitle"),
                                btn,
                                key,
                                nbnt
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

    

