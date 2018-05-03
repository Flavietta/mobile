/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.mycompany.entity.Soins;
import com.mycompany.entity.Symptomes;
import com.mycompany.services.ServicesSoins;
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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

/**
 *
 * @author majid
 */
public class UpdateSoin extends SideMenuBaseForm {
    public int k=0;
    public UpdateSoin(Resources theme, int c) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");

        Container welcome = FlowLayout.encloseCenter(
                new Label("Update, ", "WelcomeWhite"),
                new Label("Soins", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

//        Image profilePic = theme.getImage("213.png");

  //      Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new afficheSoins(theme).show();
        });
//        backbutton.addActionListener(e -> );

        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

       // profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        //Label profilePicLabel = new Label(profilePic, "ProfilePic");
        //profilePicLabel.setMask(mask.createMask());
       // profilePicLabel.getStyle().setOpacity(230);

//        TextField refc = new TextField("", "refc", 20, TextArea.NUMERIC);
        TextField titre = new TextField("", "", 20, TextField.ANY);

        

        TextArea Contenu = new TextArea("", 5, TextField.CENTER);

        Contenu.getStyle().setFgColor(0xffffff);
       
        titre.getAllStyles().setMargin(LEFT, 0);
       

        Contenu.getStyle().setBgTransparency(5);
        Contenu.setHeight(40);

        Contenu.addPointerPressedListener((evt) -> {
            Contenu.getStyle().setBgTransparency(5);
        });
        Contenu.isGrowByContent();

        Button UpdateButton = new Button("Update");
        UpdateButton.setUIID("LoginButton");
        
        
        
        
   //service conse=...
        //Conseil c=conse.getcons(c);
        Container sy=new Container(BoxLayout.y());
        
        ServicesSoins sv = new ServicesSoins();
        Soins e = sv.A3tiniSoins(c);
        
        titre.setText(e.getTitre());
        Contenu.setText(e.getDescription());
        for(Symptomes sos:e.getSympsoms()){
            
            TextField symm = new TextField("", "", 20, TextField.ANY);
            symm.setText(sos.getSymptome());
            sy.add(symm);
            k++;
        }
        
//        combo.setSelectedItem(e.getCategorie());

        
        
        
        
        
     
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!(titre.getText().equals("") && Contenu.getText().equals(""))) {
                    Soins up = new Soins();
                    up.setRefs(c);
                    up.setTitre(titre.getText());
                    up.setDescription(Contenu.getText());
                    int j=0;
                   for(Symptomes sos:e.getSympsoms()){
                    TextField sdf =(TextField)sy.getComponentAt(j);
                    
                    sos.setSymptome(sdf.getText());
                    up.addSymp(sos);
                    j++;
                    }

                    ServicesSoins update = new ServicesSoins();
                    update.updateSoins(up);
                    System.out.println("Update effectué");
                    new afficheSoins(theme).show();
                } else {
                    ToastBar.showMessage("Vous devez remplir tous le formulaire", FontImage.MATERIAL_INFO);
                }
            }
        });

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                //profilePicLabel,
                spaceLabel,
                titre,
                Contenu,
                sy,
                UpdateButton
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

    @Override
    public void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
