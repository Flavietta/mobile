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

import Produits.GUI.AfficheProduit;
import com.mycompany.services.Authentication;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import javafx.scene.paint.Material;

/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Amine
 */
public class AfficheVisitor extends SideMenuBaseForm {

    private Resources theme;
    Form f = new Form();

    public AfficheVisitor(Resources res) {
        super(new BorderLayout());
        theme = UIManager.initFirstTheme("/theme_2");
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        //tb.setUIID("Toolbarvisitor");
        //System.out.println(Authentication.currentUser.getUsername());
        Image log = res.getImage("logo2.png");
        Label profilePicLabel = new Label("", log, "SideMenuTitle");

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Button btn = new Button("");
       // btn.setUIID("Title");
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_EXIT_TO_APP);
        btn.addActionListener(e -> {

            com.mycompany.services.Authentication.logout();
            new LoginForm(res).show();

        });

        Container titleCmp = BoxLayout.encloseY(
                BorderLayout.center(
                        BoxLayout.encloseX(
                                spaceLabel
                        )
                ).add(BorderLayout.NORTH, profilePicLabel)
        //                        GridLayout.encloseIn(2, remainingTasks)
        ).add(BoxLayout.encloseX(new Label(" Besoin de soin, besoin de nous                OUT", "CenterSubTitle"), btn));

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
//        fab.getAllStyles().setMargin(BOTTOM, remainingTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        fab.setVisible(false);

        Button btncons = new Button(" Conseils");
        FontImage.setMaterialIcon(btncons, FontImage.MATERIAL_PREGNANT_WOMAN, 5);
        btncons.setUIID("LoginButton");
        btncons.addActionListener((evt) -> {
            new ListConseils(res).show();

        });
         Button btnFormations = new Button(" Formations");
        FontImage.setMaterialIcon(btnFormations, FontImage.MATERIAL_SPA, 5);
        btnFormations.setUIID("LoginButton");
        btnFormations.addActionListener((evt) -> {
            new ListFormations(theme).show();

        });
  Button btnproduits = new Button(" Produits");
        FontImage.setMaterialIcon(btnproduits, FontImage.MATERIAL_SHOPPING_BASKET, 5);
        btnproduits.setUIID("LoginButton");
        btnproduits.addActionListener((evt) -> {
            new AfficheProduit(theme).show();

        });
        Button btnsoins = new Button(" Soins");
        FontImage.setMaterialIcon(btnsoins, FontImage.MATERIAL_HEALING, 5);
        btnsoins.setUIID("LoginButton");
        btnsoins.addActionListener((evt) -> {
            new afficheSoins(theme).show();
        });
           Button btnrecomman = new Button(" Recommandations");
        FontImage.setMaterialIcon(btnrecomman, FontImage.MATERIAL_LOCAL_FLORIST, 5);
        btnrecomman.setUIID("LoginButton");
        btnrecomman.addActionListener((evt) -> {
            new ShowRecommandation(theme).show();

        });



        Container by = BoxLayout.encloseY(
                
                btncons,
                btnFormations,btnproduits,btnsoins,btnrecomman
        );
        add(BorderLayout.CENTER, by);
 by.setScrollableY(true);
        by.setScrollVisible(false);
         by.setUIID("LoginForm");
         
         
    }

 

    @Override
    public void showOtherForm(Resources res) {

    }
}
