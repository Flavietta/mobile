package Produits.GUI;


import Produits.Services.ServiceProduit;
import Produits.entities.Produits;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import static com.codename1.ui.Font.STYLE_BOLD;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.AfficheVisitor;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SideMenuBaseForm;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;

public class AjoutProduit extends SideMenuBaseForm {

     private Image img;
    
  private String imgPath;
      private AutoCompleteTextField ac;
       String fileNameInServer = "";
        private ConnectionRequest connectionRequest;

     private static final String MAPS_KEY = "AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI"; // Your maps key here
        String[] searchLocations(String text) {
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key",MAPS_KEY );
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;
}
    public AjoutProduit(Resources theme) {
        
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

      //  setUIID("LoginForm2");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout, ", "WelcomeWhite"),
                new Label("Produit", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new AfficheProduit(theme).show();
        });


        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                );
       // titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        setupSideMenu(theme);

       
        
        TextField nomp = new TextField("", "", 20, TextField.ANY);
        nomp.setHint("Nom Produit");
        nomp.getStyle().setFgColor(0x000000);
       
        
        TextField description = new TextField("", "", 20, TextField.ANY);
        description.setHint("Description");
        description.getStyle().setFgColor(0x000000);
      
        ComboBox<String> etat = new ComboBox<String>("Mauvais(e)", "Moyen(ne)", "Bon(ne)", "Excellent(e)");

//        etat.setRenderer(new GenericListCellRenderer<>(new MultiButton(""), new MultiButton("")));
        nomp.getAllStyles().setMargin(LEFT, 0);
        description.getAllStyles().setMargin(LEFT, 0);

       

        
          Button  btnAjoutImage = new Button("image");
        
        btnAjoutImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
              /*      try {
                        System.out.println("aaaa");
                        imgPath = Capture.capturePhoto();
                        img = Image.createImage(imgPath);    
                        iv.setImage(img);
                        System.out.println(imgPath);
                        String link = imgPath.toString();
                        int pod= link.indexOf("/", 2);
                        String news = link.substring(pod+2, link.length());
                        System.out.println(""+news);
                     
      FileUploader fu = new FileUploader("http://localhost:8181/PIV1/web/images");
        
        //Upload
        fileNameInServer = fu.upload(news);
                        System.out.println("-----------"+fileNameInServer+"---------------");
        
       
                     
                    } catch (IOException ex) {
                          ex.printStackTrace();
                    } catch (Exception ex) {
                    }
                }
            });*/
     
                     try {
                        imgPath = Capture.capturePhoto();
                        img = Image.createImage(imgPath);    
                       
                      // iv.setImage(img);
                         System.out.println("7aa");
                       // file = new FileUploader(imgPath);
                        System.out.println(imgPath);
                        String link = imgPath.toString();
                        int pod= link.indexOf("/", 2);
                        String news = link.substring(pod+2, link.length());
                        System.out.println(""+news);
                     
      FileUploader fu = new FileUploader("http://localhost/PIVPreFinal/web/");
        
        //Upload
        fileNameInServer = fu.upload(news);
                        System.out.println("-----------"+fileNameInServer+"---------------");
        
       
                     
                    } catch (IOException ex) {
                          ex.printStackTrace();
                    } catch (Exception ex) {
                    }
           }
            });
        
        
        
        
        
        
        
        
        
        
        
     
        Button AjoutButton = new Button("Submit");
        AjoutButton.setUIID("LoginButton");
        AjoutButton.addActionListener(e -> {
  
            if (nomp.getText() != "" && description.getText() != "" && fileNameInServer!= "") {
                Dialog.setDefaultBlurBackgroundRadius(8);
                float lan ;
                float longe;
                Coord c = detail.getCoords(ac.getText());
                
              
                   Produits pa = new Produits(nomp.getText(),fileNameInServer,description.getText(), etat.getSelectedItem() ,(float)c.getLongitude(), (float)c.getLatitude(),ac.getText()); 
                System.out.println("ggggggggggggggg . "+pa);
                ServiceProduit ajout = new ServiceProduit() ;
                
                ajout.ajoutProduit(pa);
                  Dialog.show("Well Done", "Votre produit est ajouté", "OK", null);
                new AfficheProduit(theme).show();
                
            } else {
                
                 ToastBar.showMessage("Vous devez remplir tous le formulaire" , FontImage.MATERIAL_INFO);
            
            }
        });

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        
         if(MAPS_KEY == null) {
    this.add(new SpanLabel("This demo requires a valid google API key to be set in the constant apiKey, "
            + "you can get this key for the webservice (not the native key) by following the instructions here: "
            + "https://developers.google.com/places/web-service/get-api-key"));
    this.getToolbar().addCommandToRightBar(MAPS_KEY, null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
    this.show();
    return;
}
        
        
        
        
        
        
        
        
        final DefaultListModel<String> options = new DefaultListModel<>();
       ac = new AutoCompleteTextField(options) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }
 };
        ac.setMinimumElementsShownInPopup(5);
        
        Container by = BoxLayout.encloseY(
                welcome,
               
                spaceLabel,
                nomp,
                description,
             btnAjoutImage,
                etat,
                 ac,
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
