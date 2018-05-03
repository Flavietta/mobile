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
import com.mycompany.services.ServiceConseil;
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
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.io.InputStream;


/**
 * The Login form
 *
 * @author Shai Almog
 */
public class DeleteConseil extends SideMenuBaseForm {
TextField smsnum = new TextField("");

    public DeleteConseil(Resources theme) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Delete, ", "WelcomeWhite"),
                new Label("Conseil", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("trash_can-512.png");

        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ListConseils(theme).show();
        });
//        backbutton.addActionListener(e -> );

        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        profilePicLabel.getStyle().setOpacity(230);
        TextField refc = new TextField("");
        refc.setHint("Référence");

        refc.getAllStyles().setMargin(LEFT, 0);

        Button DeleteButton = new Button("Effacer");
        DeleteButton.setUIID("LoginButton");
        DeleteButton.addActionListener(e -> {

            if (refc.getText() != "") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Conseil est Supprimé", "OK", null);

              

            } else {

                ToastBar.showMessage("Vous devez ajouter la Référence", FontImage.MATERIAL_INFO);
//                
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if (refc.getText() != "") {
                com.mycompany.services.ServiceConseil sv = new ServiceConseil();
                sv.DeleteConseil(Float.valueOf(refc.getText()));
                  new ListConseils(theme).show();
            }
                  else {
                          ToastBar.showMessage("Vous devez ajouter la Référence", FontImage.MATERIAL_INFO);
                  }
            
            }
        });
             Button SMSBut = new Button("SMS");
             SMSBut.setUIID("LoginButton");
           
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                refc,
                DeleteButton,
                        smsnum,SMSBut
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
         SMSBut.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
           String myURL = "https://rest.nexmo.com/sms/json?api_key=50ff244f&api_secret=2LGFI6ZTqEzquNiq&to=21658112495" + "&from=21658112495&text=Aminejjjjj";
                ConnectionRequest cntRqst = new ConnectionRequest() {
                   
                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "sms successfully sent", "OK", null);

                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
                System.out.println(myURL);
                System.out.println(cntRqst);
            }        
         }); }

    @Override
    public void showOtherForm(Resources res) {
    }
                 }

 
//        AuthMethod auth = new TokenAuthMethod("4dd4daea", "fmQZ2RMnrwbisEgA");
//        NexmoClient client = new NexmoClient(auth);
//        
//        if (smsnum.getText() == null) {
////            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous devez saisir un numéro ", ButtonType.OK);
//          
//        } else {
//            TextMessage message = new TextMessage("+21628112497", "+216" + smsnum.getText(),"From Bien Etre & Sante : ");
//            SmsSubmissionResult[] responses = null;
//
//            try {
//                responses = client.getSmsClient().submitMessage(message);
//                
//            } catch (IOException ex) {
//                System.out.println("fail");
//            } catch (NexmoClientException ex) {
//                System.out.println("fail");
//            }
//            for (SmsSubmissionResult response : responses) {
//                System.out.println(response);
////                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le conseil est envoyé à " + smsnum.getText() + " avec succés. ", ButtonType.OK);
//             
//            }
//        }
//    }

              