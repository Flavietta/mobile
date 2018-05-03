/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;
import static Produits.GUI.AfficheProduit.place;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Component;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author yahya
 */
public class detail {
    
     Form f = new Form();
      private static final String HTML_API_KEY = "AIzaSyDcwHdDoI65jU6iHlOGv54Efo67fE_AWw0";
      
static String address = "The White House, Washington DC";
     private Resources theme;
           

    public detail() {
    }
     public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
            
  
   
    public void detail()
    {
          theme= UIManager.initNamedTheme("/theme", "Theme");
           f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_UNDO, e -> {

            new AfficheProduit(theme).show();
        });

                 Style s = new Style();
                 s.setFgColor(0xff0000);
                 s.setBgTransparency(0);
                 FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
                 
               
                 
            
                 
              
             
               MapContainer cn = new MapContainer(); 
              // Coord x = new 
               cn.zoom(getCoords(place), 5);
            
                cn.setCameraPosition(getCoords(place)); // since the image is iin the jar this is unlikely
                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords(place), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
       
       
               f.add(cn);
               f.show();

                               
                   }

   
    
}
