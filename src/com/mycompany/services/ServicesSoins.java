/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

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
public class ServicesSoins {
    public void ajoutSoins(Soins ta,ArrayList<Symptomes> ss)  {
        int i=1;
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
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/AjoutS?desc="+newUrl+"&titre="+ta.getTitre()+"&userID="+idd+"&username="+nom; 
        for(Symptomes s:ss){
        Url=Url+"&symp"+i+"="+s.getSymptome();
        i++;
        }
        System.out.println(Url);
       
        
        
        con.setUrl(Url);



        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
  
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void deleteSoins(int ta) {
        int i=1;
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/DeleteS/"+ta; 
        
        
        
        con.setUrl(Url);



        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
  
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void updateSoins(Soins ta) {
        int i=1;
        ConnectionRequest con = new ConnectionRequest();
        String description = "";
        List<String> splitted = new ArrayList<String>();
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
            }/*
            for (String string : splitted) {
                newUrl=string+"%0A"+newUrl;
            }*/
            System.out.println("NEWURL : " + newUrl);
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/UpdateS?refs="+ta.getRefs()+"&desc="+newUrl+"&titre="+ta.getTitre()+"&userID=146&username=jsdlijdoi"; 
        
        for(Symptomes s:ta.getSympsoms()){
            System.out.println("kaaaaaaaaaaaaaaaaaaaaaaaaaaaaakaaaaa");
            System.out.println(s.getSymptome());
            System.out.println("kaaaaaaaaaaaaaaaaaaaaaaaaaaaaakaaaaa");
            Url=Url+"&symp"+i+"="+s.getSymptome();
           
            i++;
        }
       
        //    URL l=new URL(Url);
           
          //   Url=URLEncoder.encode(Url,"utf-8" );
        
         //URI uri=new URI(Url);
         System.out.println("///////////////////////");
            System.out.println("///////////////////////");
            System.out.println("$$$$$$$$$$$$$$$$"+Url+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("///////////////////////");
        //con.setUrl(uri.toASCIIString());
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public Soins A3tiniSoins(int refs) {
        int i=1;
        Soins task = new Soins();
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/hetSoin/"+refs; 
        con.setUrl(Url);
            System.out.println("hahahaha"+refs);
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
                        Map<String,Object> obj2 =new HashMap<>();
                        List<Map<String,Object>> obj3 =new ArrayList<>();
                        obj2=(Map<String,Object>)obj.get("soin");
                        
                        obj3=(List<Map<String,Object>>)obj.get("symptomes");
                        //System.out.println(obj.get("titre").toString());
                        System.out.println(obj2);
                        float refc = Float.parseFloat(obj2.get("refs").toString());
                        task.setRefs((int) refc);
                        task.setTitre(obj2.get("titre").toString());
                        task.setDescription(obj2.get("description").toString());
                        task.setUserName(obj2.get("username").toString());
                        
                        float iduser = Float.parseFloat(obj2.get("userid").toString());
                     
                        task.setUserID((int) iduser);
                     // task.setNomImage(obj.get("nomImage").toString());
                        for(Map<String,Object> obj4 :obj3){
                            Symptomes sc=new Symptomes();
                        float id = Float.parseFloat(obj4.get("id").toString());
                        float idSymp = Float.parseFloat(obj4.get("idSymp").toString());
                        sc.setId((int) id);
                        sc.setSymptome(obj4.get("symptome").toString());
                        sc.setId_symp((int) idSymp);
                        
                        task.addSymp(sc);
                        //Symps.add(sc);
                        
                        }
                        
                        

                    
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
        public void A3tiniSymptome(int refs) {
        int i=1;
        ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/hetSymp/"+refs; 
        
        
        
        con.setUrl(Url);



        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
  
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        /////////////////////////////////////    
        public List<Soins> getSoins() {
        List<Soins> listTasks = new ArrayList<>();
        List<Symptomes> Symps= new ArrayList<Symptomes>();
      
        ConnectionRequest con = new ConnectionRequest();
        
       
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/afficheSoins");
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
                    for (Map<String, Object> obj : list) {
                        Soins task = new Soins();
                       // float id = Integer.parseInt(obj.get("idweb").toString());
                        
                       // task.setId((int) id);
                        Map<String,Object> obj2 =new HashMap<>();
                        List<Map<String,Object>> obj3 =new ArrayList<>();
                        obj2=(Map<String,Object>)obj.get("soin");
                        obj3=(List<Map<String,Object>>)obj.get("symptomes");
                        //System.out.println(obj.get("titre").toString());
                     
                        float refc = Float.parseFloat(obj2.get("refs").toString());
                        task.setRefs((int) refc);
                        task.setTitre(obj2.get("titre").toString());
                        task.setDescription(obj2.get("description").toString());
                        task.setUserName(obj2.get("username").toString());
                        
                        float iduser = Float.parseFloat(obj2.get("userid").toString());
                     
                        task.setUserID((int) iduser);
                     // task.setNomImage(obj.get("nomImage").toString());
                        for(Map<String,Object> obj4 :obj3){
                            Symptomes sc=new Symptomes();
                        float id = Float.parseFloat(obj4.get("id").toString());
                        float idSymp = Float.parseFloat(obj4.get("idSymp").toString());
                        sc.setId((int) id);
                        sc.setSymptome(obj4.get("symptome").toString());
                        sc.setId_symp((int) idSymp);
                        
                        task.addSymp(sc);
                        //Symps.add(sc);
                        
                        }
                        
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
}
    public boolean checkLike(int refs,int userID){
        List<String> silly = new ArrayList<>();
        String obj2 ="";
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/checkLike?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
            
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                        
                       
                        silly.add((String)obj.get("etat"));
                       
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
         if(silly.get(0).equals("true")){
        return true;
         }else
             return false;
    
    }
     public boolean checkDislike(int refs,int userID){
        List<String> silly = new ArrayList<>();
        
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/checkDislike?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
            
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                        
                       
                        silly.add((String)obj.get("etat"));
                       
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
         if(silly.get(0).equals("true")){
        return true;
         }else
             return false;
    
    }
    public void likeOn(int refs,int userID){
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/likeOn?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
        con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void likeOff(int refs,int userID){
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/likeOff?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
        con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void disLikeOn(int refs,int userID){
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/disLikeOn?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
        con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void disLikeOff(int refs,int userID){
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/disLikeOff?userID="+userID+"&refs="+refs; 
        con.setUrl(Url);
        con.addResponseListener((e) -> {
                 String str = new String(con.getResponseData());
                 System.out.println(str);
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public int getNbLike(int refs){
        List<Integer> silly = new ArrayList<>();
        
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/countLike?refs="+refs; 
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
    
     public int getNbDislike(int refs){
        List<Integer> silly = new ArrayList<>();
        
         ConnectionRequest con = new ConnectionRequest();
        String Url ="http://localhost/PIVPreFinal/web/app_dev.php/json/countDislike?refs="+refs; 
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
     public List<Map<String,String>> getUsersLike(int refs){
          
        List<Map<String,String>> listduo= new ArrayList<Map<String,String>>();
      
        ConnectionRequest con = new ConnectionRequest();
        
       
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/getUsersLike?refs="+refs);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    
                    List<Map<String, String>> list = (List<Map<String, String>>) tasks.get("root");
                    
                       
                    for (Map<String, String> obj : list) {
                    listduo.add(obj);
                        System.out.println("names only  "+obj.get("name"));
                    
                    }
                      } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         //System.out.println("5555555555555555555555555555"+listduo);
        return listduo;

                }
       
     public List<Map<String,String>> getUsersDislike(int refs){
          
        List<Map<String,String>> listduo= new ArrayList<Map<String,String>>();
      
        ConnectionRequest con = new ConnectionRequest();
        
       
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/getUsersDislike?refs="+refs);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, String>> list = (List<Map<String, String>>) tasks.get("root");
                    for (Map<String, String> obj : list) {
                    listduo.add(obj);
                        System.out.println(obj.get("name"));
                    
                    }
                      } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         //System.out.println("5555555555555555555555555555"+listduo);
        return listduo;

                }
       public List<Soins> getMesSoins() {
        List<Soins> listTasks = new ArrayList<>();
        List<Symptomes> Symps= new ArrayList<Symptomes>();
        int userID = Authentication.currentUser.getId();
        ConnectionRequest con = new ConnectionRequest();
        
       
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/afficheMesSoins?userID="+userID);
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
                    for (Map<String, Object> obj : list) {
                        Soins task = new Soins();
                       // float id = Integer.parseInt(obj.get("idweb").toString());
                        
                       // task.setId((int) id);
                        Map<String,Object> obj2 =new HashMap<>();
                        List<Map<String,Object>> obj3 =new ArrayList<>();
                        obj2=(Map<String,Object>)obj.get("soin");
                        obj3=(List<Map<String,Object>>)obj.get("symptomes");
                        //System.out.println(obj.get("titre").toString());
                     
                        float refc = Float.parseFloat(obj2.get("refs").toString());
                        task.setRefs((int) refc);
                        task.setTitre(obj2.get("titre").toString());
                        task.setDescription(obj2.get("description").toString());
                        task.setUserName(obj2.get("username").toString());
                        
                        float iduser = Float.parseFloat(obj2.get("userid").toString());
                     
                        task.setUserID((int) iduser);
                     // task.setNomImage(obj.get("nomImage").toString());
                        for(Map<String,Object> obj4 :obj3){
                            Symptomes sc=new Symptomes();
                        float id = Float.parseFloat(obj4.get("id").toString());
                        float idSymp = Float.parseFloat(obj4.get("idSymp").toString());
                        sc.setId((int) id);
                        sc.setSymptome(obj4.get("symptome").toString());
                        sc.setId_symp((int) idSymp);
                        
                        task.addSymp(sc);
                        //Symps.add(sc);
                        
                        }
                        
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
}
     
}
