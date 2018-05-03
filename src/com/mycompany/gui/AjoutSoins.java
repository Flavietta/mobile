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

import com.mycompany.entity.Soins;
import com.mycompany.entity.Symptomes;
import com.mycompany.services.Authentication;
import com.mycompany.services.ServicesSoins;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class AjoutSoins extends Form {

    public ComboBox<Integer> combo;
    public Container by;
    public Container in;
    public Container sympts;
    public int i = 0;
    public ArrayList<TextField> listTF = new ArrayList<TextField>();
    public ArrayList<Symptomes> listSymp = new ArrayList<Symptomes>();
    ServicesSoins sss =new ServicesSoins();

    public AjoutSoins(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite"),
                new Label("Soin", "WelcomeBlue")
        );
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        getTitleArea().setUIID("Container");
                tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new afficheSoins(theme).show();
        });

        
        TextField titre = new TextField("", "", 20, TextField.ANY);
        titre.setHint("Titre");

        
        TextArea description = new TextArea("", 5, TextField.CENTER);
        description.setHint("Description");
        description.getStyle().setFgColor(0xffffff);
        SpanLabel nbS=new SpanLabel("nb Symtpome : ");
        nbS.setUIID("CenterSubTitle");
        combo = new ComboBox<Integer>(1, 2, 3, 4, 5);

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(""), new MultiButton("")));
        combo.setPreferredH(35);
        titre.getAllStyles().setMargin(LEFT, 0);
        

        description.getStyle().setBgTransparency(5);
        description.setHeight(40);

        description.addPointerPressedListener((evt) -> {
            description.getStyle().setBgTransparency(5);
        });
        description.isGrowByContent();

       
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        /////////////////////////////////////////
        TextField f = new TextField("", "", 20, TextField.ANY);
        f.setHint("symptome1");
        listTF.add(f);
        sympts = BoxLayout.encloseY(f);
        
        combo.addActionListener((evt) -> {

            ArrayList<TextField> backUpList = new ArrayList<TextField>();
            backUpList.addAll(listTF);

            listTF.clear();
            sympts.removeAll();
            for (i = 0; i < combo.getSelectedItem(); i++) {
                TextField ff = new TextField("", "", 20, TextField.ANY);
                int j = i + 1;
                ff.setHint("symptome" + j);

                if (backUpList.size() > i) {
                    ff.setText(backUpList.get(i).getText());

                }

                listTF.add(ff);

            }
            for (TextField tt : listTF) {
                sympts.add(tt);
            }

        });

        ///////////////////////////////////////
        // for low res and landscape devices
        in = BoxLayout.encloseY(
                welcome,
               
                spaceLabel,
                titre,
                description,
                nbS,
                combo,
                sympts
        );
        by = BoxLayout.encloseY(in);
         Button AjoutButton = new Button("Ajouter");
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {

            if (titre.getText() != "" && description.getText() != "" && listTF.get(0).getText() != "") {
                Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("Well Done", "Votre Soin est ajouté", "OK", null);

                Toolbar.setGlobalToolbar(false);
               Soins c = new Soins(titre.getText(), description.getText(), Authentication.currentUser.getId(), Authentication.currentUser.getUsername());
               
                for (TextField tt : listTF) {
               
                    Symptomes s=new Symptomes(tt.getText());
                    listSymp.add(s);
            }
                sss.ajoutSoins(c,listSymp);
            
                

                Toolbar.setGlobalToolbar(true);
            } else {
                Dialog.setDefaultBlurBackgroundRadius(8);

                Dialog.show("Attention", "Vous devez remplir tous le formulaire", "OK", null);
            }
            new afficheSoins(theme).show();
        });
        by.add(AjoutButton);
        add(BorderLayout.CENTER, by);
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
