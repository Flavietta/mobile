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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 * The Login form
 *
 * @author Amine
 */
public class CommentList extends SideMenuBaseForm {

    Form f;
    SpanLabel lb;

    public CommentList(Resources theme, int c) {
        
           super(BoxLayout.y());
        Toolbar tb = getToolbar();
        f = new Form();
//        tb.setUIID("Toolbar1");
        tb.setTitleCentered(false);
        Button bb = new Button("");
        FontImage.setMaterialIcon(bb, FontImage.MATERIAL_ADD_BOX);
        Image log = theme.getImage("comm.png");
        Label profilePicLabel = new Label("           ", log, "Title");

        Button btn = new Button("");
        btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_ADD_BOX);
        btn.addActionListener(e -> {

            new AjoutConseil(theme).show();

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
        backBut.setUIID("Title");
        FontImage.setMaterialIcon(backBut, FontImage.MATERIAL_UNDO);
        backBut.addActionListener((evt) -> {
                new AfficheDetailsTry(theme, c).show();
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
          ctn.setScrollableY(true);
         ctn.setScrollableY(focusScrolling);

        f.add(lb);
        com.mycompany.services.ServiceConseil sr = new ServiceConseil();



        ArrayList<post> lis = (ArrayList<post>) sr.getListpost(c);
        String s = "*************************************************************************************************************";

        for (post R : lis) {

            Container c1 = new Container(BoxLayout.y());

            Button btndel = new Button("");
            btndel.setUIID("Titlebtn");
            SpanLabel id = new SpanLabel("" + R.getId());
            FontImage.setMaterialIcon(btndel, FontImage.MATERIAL_DELETE);
            btndel.addActionListener(e -> {

                com.mycompany.services.ServiceConseil sv = new ServiceConseil();
                float foo = Float.valueOf(id.getText());
                ToastBar.showMessage("Votre Commentaire est Supprimé avec Succès", FontImage.MATERIAL_INFO);
                    sv.DeleteCmment(foo);
                    new CommentList(theme, c).show();

               
            });

            SpanLabel desc = new SpanLabel("'' " + R.getDescription()+" ''");
            desc.setTextUIID("comdesc1");
            SpanLabel nomuser = new SpanLabel("From :  " + R.getNom_user());
            //SpanLabel idcons = new SpanLabel("Id cons : " + R.getId_conseil());
            String nom = Authentication.currentUser.getUsername();
            if(nom.equals(R.getNom_user())){
              c1.add(new Label("")).add(desc).add(nomuser).add(btndel);  
            }
            else {
                c1.add(new Label("")).add(desc).add(nomuser);
            }

            ctn.add(c1);

        }
        String e = "////////////////////////////////////////////////////////////////////////////////////////////////////";
        Style st = new Style();
        st.setBgColor(0);
        
        Container titleCmp = BoxLayout.encloseX(
                BorderLayout.east(
                        //                        

                        BoxLayout.encloseX(
                             

                        )
                ).add(BorderLayout.WEST, profilePicLabel).add(BorderLayout.EAST, backBut)
        );

        ctn.setUIID("ctnTitle1");

        add(ctn);
        ctn.setUIID("LoginForm66");

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        tb.setTitleComponent(titleCmp);
      
       

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    public void showOtherForm(Resources res) {
    }

}
