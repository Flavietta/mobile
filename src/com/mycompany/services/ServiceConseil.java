/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entity.Conseil;
import com.mycompany.entity.post;
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
 * @author Amine
 */
public class ServiceConseil {

    public List<Conseil> getList2() {
        List<Conseil> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/affichageJson");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Conseil task = new Conseil();
                        // float id = Integer.parseInt(obj.get("idweb").toString());

                        // task.setId((int) id);
//                        System.out.println(obj.get("titre").toString());

                        float refc = Float.parseFloat(obj.get("refc").toString());
                        task.setRefc((int) refc);
                        task.setTitre(obj.get("titre").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setNomuser(obj.get("nomuser").toString());
                        task.setCategorie(obj.get("categorie").toString());
                        float iduser = Float.parseFloat(obj.get("iduser").toString());

                        task.setIduser((int) iduser);
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

    public void udpateC(Conseil ta) {
        ConnectionRequest con = new ConnectionRequest();
        String nom = Authentication.currentUser.getUsername();
        int idus = Authentication.currentUser.getId();
      
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/" + ta.getRefc() + "/UpdateP?titre=" + ta.getTitre() + "&iduser="+idus+"&nomuser="+nom+"&description=" + ta.getDescription() + "&categorie=" + ta.getCategorie() + "&contenu=" + ta.getContenu();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void DeleteConseil(float ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/DeleteC/" + ta;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ajoutConseil(Conseil ta) {
        ConnectionRequest con = new ConnectionRequest();
          String nom = Authentication.currentUser.getUsername();
        int idus = Authentication.currentUser.getId();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/Ajout?titre=" + ta.getTitre() + "&iduser="+idus+"&nomuser="+nom+"&description=" + ta.getDescription() + "&categorie=" + ta.getCategorie() + "&contenu=" + ta.getContenu();

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public Conseil AfficheDetails(float ta) {
        Conseil task = new Conseil();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/FindC/" + ta;
        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    /* List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    System.out.println("hhhhh"+list);*/
                    //for (Map<String, Object> obj : list) {
                    // float id = Integer.parseInt(obj.get("idweb").toString());
                    // task.setId((int) id);
//                    System.out.println(obj.get("titre").toString());

                    float refc = Float.parseFloat(obj.get("refc").toString());
                    task.setRefc((int) refc);
                    task.setTitre(obj.get("titre").toString());
                    task.setDescription(obj.get("description").toString());
                    task.setNomuser(obj.get("nomuser").toString());
                    task.setCategorie(obj.get("categorie").toString());
                    task.setContenu(obj.get("contenu").toString());
                    float iduser = Float.parseFloat(obj.get("iduser").toString());

                    task.setIduser((int) iduser);
                    // task.setNomImage(obj.get("nomImage").toString());

                    //}
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return task;
    }

    public void ajoutCommentaire(post ta) {
        ConnectionRequest con = new ConnectionRequest();
        String nom = Authentication.currentUser.getUsername();
        int idus = Authentication.currentUser.getId();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/AjoutC?description=" + ta.getDescription() + "&id_conseil=" + ta.getId_conseil() + "&nom_user=" + nom;

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
//
    public List<post> getListpost(float ta) {
        List<post> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/json/FindComment/"+ta);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        post task = new post();
                        // float id = Integer.parseInt(obj.get("idweb").toString());

                        // task.setId((int) id);
//                        System.out.println(obj.get("description").toString());

                        float id = Float.parseFloat(obj.get("id").toString());
                        task.setId((int) id);
                        task.setDescription(obj.get("description").toString());
                        float idconseil = Float.parseFloat(obj.get("idConseil").toString());
                        task.setId_conseil((int) idconseil);
                       
                        task.setNom_user(obj.get("nomUser").toString());

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

//    public post AfficheDetailsPost(float ta) {
//        post task = new post();
//        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost:8080/PIV1/web/app_dev.php/json/FindComment/" + ta;
//        con.setUrl(Url);
//
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                // listTasks = getListTask(new String(con.getResponseData()));
//                JSONParser jsonp = new JSONParser();
//
//                try {
//                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//
//                    //System.out.println(tasks);
//                    /* List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
//                    System.out.println("hhhhh"+list);*/
//                    //for (Map<String, Object> obj : list) {
//                    // float id = Integer.parseInt(obj.get("idweb").toString());
//                    // task.setId((int) id);
////                    System.out.println(obj.get("titre").toString());
//
//                    float id = Float.parseFloat(obj.get("id").toString());
//                    task.setId((int) id);
//               
//                        task.setDescription(obj.get("description").toString());
//                        float idconseil = Float.parseFloat(obj.get("id_conseil").toString());
//                        task.setId_conseil((int) idconseil);
//             
//                        task.setNom_user(obj.get("nomuser").toString());
//                    // task.setNomImage(obj.get("nomImage").toString());
//
//                    //}
//                } catch (IOException ex) {
//                }
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return task;
//    }
       public void DeleteCmment(float ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/json/DeletePoste/" + ta;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
