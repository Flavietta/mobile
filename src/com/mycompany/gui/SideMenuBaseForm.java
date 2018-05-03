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
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.Authentication;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {

        Stat a = new Stat();
        StatDislike b = new StatDislike();
        FileSystemStorage fs = FileSystemStorage.getInstance();
        FileSystemStorage.getInstance().delete(fs.getAppHomePath() + "pdf-sample.jpg");
        String fileName = fs.getAppHomePath() + "pdf-sample.jpg";
        if (!fs.exists(fileName)) {

            Util.downloadUrlToFile("http://localhost/PIV1/web/img/" + Authentication.currentUser.getImage(), fileName, true);
        }
        Image profilePic = null;
        try {
            profilePic = Image.createImage(fileName);
        } catch (IOException ex) {

        }

        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_HOME, e -> new AfficheVisitor(res).show());
        getToolbar().addMaterialCommandToSideMenu("  DisLike Stats", FontImage.MATERIAL_SETTINGS, e -> b.createPieChartForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Like Stats", FontImage.MATERIAL_SETTINGS, e -> a.createPieChartForm().show());

        getToolbar().addMaterialCommandToSideMenu("  Edit Profile", FontImage.MATERIAL_FACE, e -> new ListConseils(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Conseils", FontImage.MATERIAL_PREGNANT_WOMAN, e -> new ListConseils(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Formations", FontImage.MATERIAL_SPA, e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Produits", FontImage.MATERIAL_SHOPPING_BASKET, e -> new AfficheProduit(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Recommandations", FontImage.MATERIAL_HEALING, e -> new ShowRecommandation(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS, e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Soins", FontImage.MATERIAL_LOCAL_FLORIST, e -> new afficheSoins(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            Authentication.logout();
            new LoginForm(res).show();
        });

    }

    protected abstract void showOtherForm(Resources res);
}
