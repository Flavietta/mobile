/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entity.Conseil;
import com.mycompany.services.ServiceConseil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author Amine
 */
public class TryAffiche {

    Form f;
    SpanLabel lb;

    public TryAffiche() {

        f = new Form();
        lb = new SpanLabel("");
        Container ctn = new Container(BoxLayout.y());
        ctn.setScrollableY(true);

        f.add(lb);
        com.mycompany.services.ServiceConseil sr = new ServiceConseil();

        ArrayList<Conseil> lis = (ArrayList<Conseil>) sr.getList2();
        String s = new String("-------------------");
        for (Conseil R : lis) {
            
            Container c1 = new Container(BoxLayout.y());
   

            SpanLabel refc = new SpanLabel("Référence :"+R.getRefc());
            SpanLabel titre = new SpanLabel("Titre:" + R.getTitre());
            SpanLabel description = new SpanLabel("Description : " + R.getDescription());
            SpanLabel iduser = new SpanLabel("Id USer : " + R.getIduser());
            SpanLabel nomuser = new SpanLabel("Nom USer : " + R.getNomuser());
            SpanLabel categorie = new SpanLabel("Categorie : " + R.getCategorie());
            c1.add(refc).add(titre).add(description).add(iduser).add(nomuser).add(categorie);
            c1.getStyle().setBorder(Border.createLineBorder(10, 0xFF0000));
            ctn.add(c1);
         

        }
        Style st = new Style();
        st.setBgColor(0);
        f.add(ctn);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
