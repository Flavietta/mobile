/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Recommandation;
import com.mycompany.entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 *
 * @author FALAVIO
 */
public class RecommandationController {
    
    public static List<Recommandation> getAll(){
        ArrayList<Recommandation> listRecs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIV1/web/app_dev.php/api/rec/all/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> recs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(users);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) recs.get("root");
                    //System.out.println("LIST => " + list.toString());
                    //System.out.println("FIRST IN FIRST => " + Float.parseFloat(list.get(0).get("id").toString()));
                    for (Map<String, Object> obj : list) {
                        Recommandation rec = new Recommandation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());
                        
                        rec.setId((int) id);
                        rec.setFile(obj.get("file").toString());
                        rec.setDescription(obj.get("description").toString());
                        rec.setTitle(obj.get("titre").toString());
                        rec.setFile(obj.get("file").toString());
                        //rec.setRateList(rateList);
                        User owner = new User();
                        rec.setRateList(RateController.getAll(rec));
                        rec.setOwner(UserController.getUser((Map<String,Object>)obj.get("ownerid")));
                        
                        listRecs.add(rec);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecs;
        
    }
    
     public static void addRec(Recommandation rec,String file){
        String filestack = "http://localhost/PIV1/web/app_dev.php/api/rec/pdfupload/?file="+rec.getTitle();
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
           }
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
             request.addData("file", file, "application/pdf");
            request.setFilename("file", "Rec.pdf");
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch(IOException err) {
            err.printStackTrace();
        }
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/rec/add?desc=" + rec.getDescription() + "&title=" + rec.getTitle()+ "&idUser=" + rec.getOwner().getId()+"&file=" + rec.getFile();
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public static void updateRec(Recommandation rec,String descri , String title){
        if (Authentication.currentUser!=null) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/rec/update?desc="+descri+"&title=" + title + "&id=" + rec.getId() +"&file=" + rec.getFile();
        
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    }
    
    public static void deleteRec(Recommandation rec){
        if (Authentication.currentUser!=null) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/rec/delete?id="+ rec.getId() ;
        con.setUrl(Url);
        
        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    }
    
    public static Recommandation getRecById(int id){
        for (Recommandation object : getAll()) {
            if (object.getId()==id) {
                return object;
            }
        }
        return null;
    }
    
    public static Recommandation getRec(Map<String,Object> obj){
        Recommandation rec = new Recommandation();
        float id = Float.parseFloat(obj.get("id").toString());
        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());

        rec.setId((int) id);
        rec.setDescription(obj.get("description").toString());
        rec.setTitle(obj.get("titre").toString());
        rec.setFile(obj.get("file").toString());
        //rec.setRateList(rateList);
        User owner = new User();

        rec.setOwner(UserController.getUser((Map<String,Object>)obj.get("ownerid")));
        
        return rec;
    }
    
}
