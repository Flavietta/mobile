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
import com.mycompany.services.Authentication;
import com.mycompany.services.ServiceConseil;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import java.util.ArrayList;


/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Amine
 */
public class ListConseils extends SideMenuBaseForm {
//
//    private Resources theme;

    public float so;
    Form f;
    SpanLabel lb;

    public ListConseils(Resources theme) {

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
       // tb.setUIID("Toolbar1");
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
        Image log = theme.getImage("liste3.png");
        Label profilePicLabel = new Label("", log, "Title");

        Button btn = new Button("");
       // btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {

            new AjoutConseil(theme).show();

        });

        Button menuButton = new Button("");
     //   menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);

        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent
                = BorderLayout.west(
                        BorderLayout.north(menuButton)
                );
        Button backBut = new Button("");
       // backBut.setUIID("Title");
        FontImage.setMaterialIcon(backBut, FontImage.MATERIAL_UNDO);
        backBut.addActionListener((evt) -> {
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
        com.mycompany.services.ServiceConseil sr = new ServiceConseil();

        ArrayList<Conseil> lis = (ArrayList<Conseil>) sr.getList2();

        String s = "*************************************************************************************************************";
        for (Conseil R : lis) {

            Container c1 = new Container(BoxLayout.y());

            SpanLabel refc = new SpanLabel("" + R.getRefc());

            String tit = "Titre : ";
            String descc = "Description : ";
            String catego = "Categorie : ";

            SpanLabel titre = new SpanLabel(tit + R.getTitre());

            SpanLabel description = new SpanLabel(descc + R.getDescription());
//            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
//            SpanLabel nomuser = new SpanLabel("Nom USer : " + R.getNomuser());
            Button btndel = new Button("");
          //  btndel.setUIID("Titlebtn");
            FontImage.setMaterialIcon(btndel, FontImage.MATERIAL_DELETE);
            btndel.addActionListener(e -> {
                Conseil c = new Conseil(refc.getText());
                com.mycompany.services.ServiceConseil sv = new ServiceConseil();
                float foo = Float.valueOf(refc.getText());
                if (true) {
                    sv.DeleteConseil(foo);
                    new ListConseils(theme).show();
                    ToastBar.showMessage("Votre Conseil est Supprimé avec Succès", FontImage.MATERIAL_INFO);
                } else {
                    Dialog.setDefaultBlurBackgroundRadius(8);
                    Dialog.show("Erreur", "Votre Conseil n'est pas supprimé", "OK", null);
                }
            });

            Button btnupda = new Button("");
        //    btnupda.setUIID("Titleupdatebtn");
            FontImage.setMaterialIcon(btnupda, FontImage.MATERIAL_SPELLCHECK);
            btnupda.addActionListener(e -> {
                so = Float.valueOf(refc.getText());
//                System.out.println(refc.getText());
                new UpdateConseil1(theme, (int) so).show();

            });

            Button btnaffiche = new Button("");
         //   btnaffiche.setUIID("Titleaffichebtn");
            FontImage.setMaterialIcon(btnaffiche, FontImage.MATERIAL_VISIBILITY);
            btnaffiche.addActionListener(e -> {
                so = Float.valueOf(refc.getText());

                new AfficheDetailsTry(theme, (int) so).show();

            });
            String nom = Authentication.currentUser.getUsername();
//            System.out.println("NOMM : "+nom);
//            System.out.println("Nomm 2 : "+R.getNomuser());
            int idus = Authentication.currentUser.getId();
            Container cont = new Container();
            if(nom.equals(R.getNomuser())){
                   cont = BoxLayout.encloseX(btndel, btnupda, btnaffiche);
            }
            else{
                 cont = BoxLayout.encloseX(btnaffiche);
            }
         
            SpanLabel categorie = new SpanLabel(catego + "( " + R.getCategorie() + " )");
            c1.add(titre).add(categorie).add(description).add(cont);

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
                                new Label("Des Conseils Pour Tous              Ajout", "CenterSubTitle"),
                                btn, backBut
                        )
                ).add(BorderLayout.NORTH, profilePicLabel)
        );

      //  ctn.setUIID("ctnTitle1");

        add(ctn);
       // ctn.setUIID("LoginForm66");

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
