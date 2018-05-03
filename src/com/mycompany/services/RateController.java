/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.Rate;
import com.mycompany.entity.Recommandation;
import com.mycompany.entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FALAVIO
 */
public class RateController {
    
    
    public static List<Rate> getAll(Recommandation rec){
        ArrayList<Rate> listRate = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIV1/web/app_dev.php/api/rate/all/?idReco="+rec.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> rates = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(users);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) rates.get("root");
                    //System.out.println("LIST => " + list.toString());
                    //System.out.println("FIRST IN FIRST => " + Float.parseFloat(list.get(0).get("id").toString()));
                    for (Map<String, Object> obj : list) {
                        Rate rate = new Rate();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());
                        rate.setId((int) id);
                        float rating = Float.parseFloat(obj.get("rate").toString());
                        
                        rate.setRate((int) rating);
                        rate.setRec(RecommandationController.getRec((Map<String,Object>)obj.get("idReco")));
                        rate.setVotedUser(UserController.getUser((Map<String,Object>)obj.get("idUser")));
                        
                        listRate.add(rate);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRate;
        
    }
    
    
     public static void addRate(Rate rate,Recommandation rec){
         if (rec.getRateList().contains(rate)) {
             updateRate(rate,rate.getRate() );
            List<Rate> list = rec.getRateList();
            list.remove(rate);
            list.add(rate); 
            rec.setRateList(list);
        
         }else{
            ConnectionRequest con = new ConnectionRequest();
            String Url = "http://localhost/PIV1/web/app_dev.php/api/rate/add?idReco=" + rate.getRec().getId()+ "&rate=" + rate.getRate()+ "&idUser=" + rate.getVotedUser().getId();
            con.setUrl(Url);

            System.out.println("");

            con.addResponseListener((e) -> {
                String str = new String(con.getResponseData());
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
            List<Rate> list = rec.getRateList();
            list.add(rate); 
            rec.setRateList(list);
        
         }
        
     }
    
    public static void updateRate(Rate rate,int rating ){
        if (Authentication.currentUser!=null) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/rate/update?idReco=" + rate.getRec().getId()+ "&idUser=" + rate.getVotedUser().getId()+ "&rate=" + rating;
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    }
    /*
    public static void deleteRate(Rate rate){
        if (Authentication.currentUser!=null) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/rate/delete?id="+ rate.getId() ;
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    }*/
}
