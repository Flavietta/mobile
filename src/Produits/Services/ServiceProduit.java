/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.Services;


import Produits.GUI.Login;
import Produits.entities.wishlist;
import Produits.entities.Produits;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yahya
 */
public class ServiceProduit {
    
    
    
       public List<Produits> getList2() {
        List<Produits> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/allproduit");
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
                        Produits task = new Produits();
                        float id = Float.parseFloat(obj.get("idp").toString());
                        
                     task.setId((int) id);
                        System.out.println(obj.get("nomp").toString());
                        task.setNomp(obj.get("nomp").toString());
                        task.setUsername(obj.get("username").toString());
                        task.setNomImage(obj.get("webPath").toString());
                        task.setEtat(obj.get("etat").toString());
                        task.setDescription(obj.get("description").toString());
                         String ville =obj.get("ville").toString();
                            System.out.println(ville+"---------------------");
                              task.setVille(ville);
                        float userid = Float.parseFloat(obj.get("userid").toString());
                        
                        
                         task.setUserID((int)userid);
  
                     
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
       
    
    public void udpateP(Produits ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/"+ta.getId()+"/UpdateP?nomp="+ta.getNomp()+"&username="+Login.usernamPUB+"&userid="+Login.useridPUB+"&description="+ta.getDescription()+"&etat="+ta.getEtat() ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
       
    
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
      
      
     
    public void ajoutProduit(Produits ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/Ajout?nom="+ta.getNomp()+"&username="+Login.usernamPUB+"&description="+ta.getDescription()+"&userid="+Login.useridPUB+"&etat="+ta.getEtat()+"&nomImage="+ta.getNomImage()+"&long="+ta.getLongitude()+"&lat="+ta.getLatitude()+"&ville="+ta.getVille();
        System.out.println("jjjjjjjjjjjjjjjj            "+Url);
        con.setUrl(Url);

        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   
    
    
    
   
    public void DeleteProduit(Produits ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/DeleteP/"+ta.getId() ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   
    
    public void Panier(Produits ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/AjoutPanier?wanter="+Login.useridPUB+"&IdProduitPanier="+ta.getId()+"&Nomproduit="+ta.getNomp()+"&idowner="+ta.getUserID()+"&wantername="+Login.usernamPUB+"&owner="+ta.getUsername(); 
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    
    
    
    /// 
    
    
    public List<Produits> getListowner() {
        List<Produits> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/allproduit");
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
                        Produits task = new Produits();
                        
                        if (Login.usernamPUB.equals(obj.get("username").toString())){
                        float id = Float.parseFloat(obj.get("idp").toString());
                       
                     task.setId((int) id);
                        System.out.println(obj.get("nomp").toString());
                        task.setNomp(obj.get("nomp").toString());
                        task.setUsername(obj.get("username").toString());
                        task.setNomImage(obj.get("nomImage").toString());
                        task.setEtat(obj.get("etat").toString());
                        task.setDescription(obj.get("description").toString());
                          
                     // task.setNomImage(obj.get("nomImage").toString());
                        
                        listTasks.add(task);
                        }
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    ///////////
    
    
    public static int nbr  ; 
    
//      public int compteurPanier() {
//       
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/CompteurPanier/"+Authentication.currentUser.getId());
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               // listTasks = getListTask(new String(con.getResponseData()));
//                JSONParser jsonp = new JSONParser();
//                
//                try {
//                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks);
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    for (Map<String, Object> obj : list) {
//                        
//                  
//                        float num = Float.parseFloat(obj.get("nb").toString());
//                        
//                    nbr = (int)num ;
//                        
//                    }
//                }
//                catch (IOException ex) {
//                }
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return nbr;
//    }
//    
//    
    
    
    /// wishlist 
       
    public List<wishlist> wishlist() {
        List<wishlist> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/affPanier/"+Login.useridPUB);
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
                        wishlist task = new wishlist() ;
                        
                        if (Login.usernamPUB.equals(obj.get("wantername").toString())){
                        float id = Float.parseFloat(obj.get("idpanier").toString());
                        
                        task.setPanier((int) id);
                     
                     
                        float wanterid = Float.parseFloat(obj.get("wanter").toString());
                        
                        task.setWanter((int) wanterid);
                     
                        
                        float idproduitpanier = Float.parseFloat(obj.get("idproduitpanier").toString());
                        
                        task.setIdProduitPanier((int) idproduitpanier);
                     
                         float idowner = Float.parseFloat(obj.get("idowner").toString());
                        
                        task.setIdOwner((int) id);
                        
                        
                        System.out.println(obj.get("nomproduit").toString());
                        task.setNomproduit(obj.get("nomproduit").toString());
                        task.setWantername(obj.get("wantername").toString());
                        task.setOwner(obj.get("owner").toString());
                     
                         
                     // task.setNomImage(obj.get("nomImage").toString());
                        
                        listTasks.add(task);
                        }
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    // notification
    
    
    
    
     public List<wishlist> notification() {
        List<wishlist> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/affNotif?idowner="+Login.useridPUB);
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
                        wishlist task = new wishlist() ;
                        
                     
                        float id = Float.parseFloat(obj.get("idpanier").toString());
                        
                        task.setPanier((int) id);
                     
                     
                        float wanterid = Float.parseFloat(obj.get("wanter").toString());
                        
                        task.setWanter((int) wanterid);
                     
                        
                        float idproduitpanier = Float.parseFloat(obj.get("idproduitpanier").toString());
                        
                        task.setIdProduitPanier((int) idproduitpanier);
                     
                         float idowner = Float.parseFloat(obj.get("idowner").toString());
                        
                        task.setIdOwner((int) id);
                        
                        
                        System.out.println(obj.get("nomproduit").toString());
                        task.setNomproduit(obj.get("nomproduit").toString());
                        task.setWantername(obj.get("wantername").toString());
                        task.setOwner(obj.get("owner").toString());
                     
                            System.out.println("qqaaaaaaa");
                     // task.setNomImage(obj.get("nomImage").toString());
                        
                        listTasks.add(task);
                   
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    
    public static int nbr1  ; 
    
//      public int compteurnotif() {
//       
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/CompteurNotif?idowner="+Login.useridPUB);
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               // listTasks = getListTask(new String(con.getResponseData()));
//                JSONParser jsonp = new JSONParser();
//                
//                try {
//                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
//                    //System.out.println(tasks);
//                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    for (Map<String, Object> obj : list) {
//                        
//                  
//                        float num = Float.parseFloat(obj.get("nb").toString());
//                        
//                    nbr1 = (int)num ;
//                        
//                    }
//                }
//                catch (IOException ex) {
//                }
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return nbr1;
//    }
    
    
    
    
    // accepter notif
      
      
       
    public void AccepterN(wishlist ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/acceptNotJson?idproduit="+ta.getIdProduitPanier()+"&wanterid="+ta.getWanter()+"&wantername="+ta.getWantername()+"&idpanier="+ta.getPanier() ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
       
    
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    
    
    public void DeletePanier(wishlist ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/refuseNotJson?idpanier="+ta.getPanier() ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    
    
    
    
    
    
    
    
}





