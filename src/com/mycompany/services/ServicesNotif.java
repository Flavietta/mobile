/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entity.Notif_soin;
import com.mycompany.entity.Soins;
import com.mycompany.entity.Symptomes;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author majid
 */
public class ServicesNotif {
    
    
            public Soins getLastAdded() {
        int i=1;
        Soins task = new Soins();
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/getLastAdded"; 
        con.setUrl(Url);
            
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    //System.out.println(tasks);
                   
                        
                       // float id = Integer.parseInt(obj.get("idweb").toString());
                        
                       
                       // task.setId((int) id);
                     
                        
                        
                        //System.out.println(obj.get("titre").toString());
                        System.out.println(obj);
                        float refc = Float.parseFloat(obj.get("refs").toString());
                        task.setRefs((int) refc);
                        task.setTitre(obj.get("titre").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setUserName(obj.get("username").toString());
                        
                        float iduser = Float.parseFloat(obj.get("userid").toString());
                     
                        task.setUserID((int) iduser);
                     // task.setNomImage(obj.get("nomImage").toString());
                        
                        

                    
                 con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
  
                });
                 } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return task;
    }
            
            
            
            public void notifier (){
                ServicesNotif n=new ServicesNotif();
                Soins ta=n.getLastAdded();
            
              ConnectionRequest con = new ConnectionRequest();
        int idd = Authentication.currentUser.getId();
        String description = "";
        List<String> splitted = new ArrayList<String>();
        String nom = Authentication.currentUser.getUsername();
             int x = 0;
            for (int j = 0 ; j<ta.getDescription().length();j++) {
                if (ta.getDescription().charAt(j)!='\n') {
                    description+=ta.getDescription().charAt(j);
                }
                else{
                    splitted.add(description);
                    description="";
                }
                System.out.println("Description---------->" + description+" splitted----------->"+splitted.toString());
            }
            splitted.add(description);
            String newUrl = splitted.get(0) ;
            for (int j = 1; j < splitted.size(); j++) {
                newUrl=newUrl+"%0A"+splitted.get(j);
            }
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/Notifier?refs="+ta.getRefs()+"&desc="+newUrl+"&titre="+ta.getTitre()+"&userID="+ta.getUserID()+"&username="+ta.getUserName()+"&currentUserID="+idd; 
       
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
  
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
            public int countNbNotif(){
                
                 int idd = Authentication.currentUser.getId();
                  List<Integer> silly = new ArrayList<>();
        
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/nbNotif?currentUserID="+idd; 
        con.setUrl(Url);
            
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    int obj2=Integer.parseInt((String)obj.get("nb"));
                    silly.add(obj2);
                       
                        //System.out.println(obj.get("titre").toString());
                        System.out.println("/////////////////"+silly.get(0));
                        
                 con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
  
                });
                 } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
             return silly.get(0);
    
            }
            
            
            
             public List<Notif_soin> getNotifs() {
                 
             int idd = Authentication.currentUser.getId();    
        List<Notif_soin> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/getNotifs?currentUserID="+idd);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj2 : list) {
                        Notif_soin N=new Notif_soin();
                       // float id = Integer.parseInt(obj.get("idweb").toString());
                        
                       // task.setId((int) id);
                        
                        
                        
                        
                        //System.out.println(obj.get("titre").toString());
                     
                        float idNotif = Float.parseFloat(obj2.get("idNotif").toString());
                        float idSoins= Float.parseFloat(obj2.get("idSoins").toString());
                        float idUserSender= Float.parseFloat(obj2.get("idUserSender").toString());
                        float idUserRec= Float.parseFloat(obj2.get("idUserRec").toString());
                        N.setId_notif((int) idNotif);
                        N.setDesc(obj2.get("descSoin").toString());
                        N.setTitre(obj2.get("titre").toString());
                        N.setId_soins((int)idSoins);
                        N.setId_user_sender((int) idUserSender);
                        N.setId_user_rec((int) idUserRec);
                        N.setNom_user_sender(obj2.get("nomUserSender").toString());
                        
                     
                        
                     // task.setNomImage(obj.get("nomImage").toString());
                        
                        
                        listTasks.add(N);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
}
            public void deleteNotif(int id_soins,int id_user_rec) {
        int i=1;
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/DeleteNotif?id_soins="+id_soins+"&id_user_rec="+id_user_rec; 
        
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
  
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
