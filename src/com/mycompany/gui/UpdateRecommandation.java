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

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;

import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.CharArrayReader;
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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.codename1.util.CallbackAdapter;
import com.mycompany.entity.Recommandation;
import com.mycompany.services.RecommandationController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


/**
 * The Login form
 *
 * @author Shai Almog
 */
public class UpdateRecommandation extends SideMenuBaseForm {

    public UpdateRecommandation(Resources theme,Recommandation r ) throws IOException {
       
        super(new BorderLayout());
      //   Recommandation r = RecommandationController.getRecById(id);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite"),
                new Label("Conseil", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("heart-plus-icon.png");

        Image mask = theme.getImage("round-mask.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new ShowRecommandation(theme).show();
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
        TextField titre = new TextField("", "", 20, TextField.ANY);
        titre.setHint("Titre");

        TextArea Contenu = new TextArea("", 5, TextField.CENTER);
        Contenu.setHint("Description");
        titre.setText(r.getTitle());
        Contenu.setText(r.getDescription());
        Contenu.getStyle().setFgColor(0xffffff);
        ComboBox<String> combo = new ComboBox<String>("Bon Conseil", "Conseil Bébé", "Conseil Homme", "Conseil Femme", "Conseil Voyage", "Conseil Alimentations");

//        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(""), new MultiButton("")));
        titre.getAllStyles().setMargin(LEFT, 0);

        Contenu.getStyle().setBgTransparency(5);
        Contenu.setHeight(40);

        Contenu.addPointerPressedListener((evt) -> {
            Contenu.getStyle().setBgTransparency(5);
        });
        Contenu.isGrowByContent();
       /*
        MultipartRequest request = new MultipartRequest();
        request.setUrl("http://localhost/PIV1/web/api/rec/pdfupload/");
        System.out.println("FileSystemStorage " + FileSystemStorage.getInstance().getAppHomePath());
        request.setPost(true);
        request.addData("README.txt","file:///C:/Users/FALAVIO/Downloads/codex-guilty.gear.xrd.rev.2" , "text/plain");
        request.setFilename("file_data", "README.txt");
        request.setReadResponseForErrors(true);
        request.addResponseCodeListener(new ActionListener() {

        public void actionPerformed(ActionEvent ev) {
            try {
                NetworkEvent event = (NetworkEvent) ev;
                Log.p("Err Rsp Code->" + event.getResponseCode());
                Log.p("ResponseCodeListener:");
                Log.p(ev.toString() );
            }catch(Exception ex){
                
            }
                }
        });
        System.out.println("REQUEST : " + request.getUrl());
        NetworkManager.getInstance().addToQueue(request);
        */
       
       //////////////////////////
      /* if (FileChooser.isAvailable()) {
            Callback<String> resultURL=new CallbackAdapter<>();
        FileChooser.showOpenDialog(".xls, .csv, text/plain", e2-> {
            String file = (String)e2.getSource();
            String picture = Capture.capturePhoto(1024, -1);
            if(picture!=null){
                System.out.println("File name : " + (String)e2.getSource());
                String filestack = "http://localhost/PIV1/web/app_dev.php/api/rec/pdfupload/?file=README.txt";
                MultipartRequest request = new MultipartRequest() {
                   protected void readResponse(InputStream input) throws IOException  {
                   /*    JSONParser jp = new JSONParser();
                       System.out.println("JSON : " + jp);
                       Map<String, Object> result = jp.parseJSON(new InputStreamReader(input, "UTF-8"));
                      String url = (String)result.get("url");
                      /*
                      if(url == null) {
                         resultURL.onError(null, null, 1, result.toString());
                         return;
                      }
                      resultURL.onSucess(url);
                  
                   Map<String, Object> recs = jp.parseJSON(new CharArrayReader(new String(this.getResponseData()).toCharArray()));
                    System.out.println("JSON : " + (List<Map<String, Object>>)recs.get("root"));
                */
             /*      }
                };
                
                request.setUrl(filestack);
                request.addResponseListener(new ActionListener<NetworkEvent>() {
                    
                public void actionPerformed(NetworkEvent ev) {
                                  JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> recs = jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));
                    System.out.println("JSON : " + ((List<Map<String, Object>>)recs.get("root")).get(0).values());
                }catch(Exception ex){

                    }
                }
                });
                try {
                    request.addData("file", file, "README.txt");
                    request.setFilename("file", "README.txt");
                    NetworkManager.getInstance().addToQueueAndWait(request);
                } catch(IOException err) {
                    err.printStackTrace();
                }
            }
        });
       }*/
       //////////////////////////
       /* if (FileChooser.isAvailable()) {
        FileChooser.showOpenDialog(".xls, .csv, text/plain", e2-> {
            String file = (String)e2.getSource();
            if (file == null) {
                add("No file was selected");
                revalidate();
            } else {
               String extension = null;
               if (file.lastIndexOf(".") > 0) {
                   extension = file.substring(file.lastIndexOf(".")+1);
               }
               if ("txt".equals(extension)) {
                   
                   FileSystemStorage fs = FileSystemStorage.getInstance();
                   try {
                       InputStream fis = fs.openInputStream(file);
                       addComponent(new SpanLabel(Util.readToString(fis)));
                       System.out.println("THE FILE SELECTED : " + file + " AND THERE'S ALSO : "+Util.readToString(fis));
                   } catch (Exception ex) {
                       
                   }
               } else {
                   add("Selected file "+file);
               }
            }
            revalidate();
        });
    }*/
     
        Button AjoutButton = new Button("Update");
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (titre.getText() != "" && Contenu.getText() != "") {
                try{
                RecommandationController.updateRec(r, Contenu.getText(), titre.getText());
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Well Done", "Votre Conseil est ajouté", "OK", null);
                }catch(Exception ex){
                Dialog.setDefaultBlurBackgroundRadius(8);
                Dialog.show("Error", "Un erreur D'ajout ", "OK", null);
                    
                }
                new ShowRecommandation(theme).show();
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
//                
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
                profilePicLabel,
                spaceLabel,
                titre,
                Contenu,
                combo,
                AjoutButton
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
