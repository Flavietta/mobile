/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entity.Formation;
import com.mycompany.entity.Participation;

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


public class FormationService {
  public ArrayList<Formation> getList2() {
        ArrayList<Formation> listform = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIVPreFinal/web/app_dev.php/affmob");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> forms = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(forms);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) forms.get("root");
                    for (Map<String, Object> obj : list) {
                        Formation task = new Formation();
                        float id = Float.parseFloat(obj.get("referencef").toString());
                        
                        task.setReferencef((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setDomaine(obj.get("domaine").toString());
                        task.setDescription(obj.get("description").toString());
                        
                        task.setDate(obj.get("date").toString());
                    task.setNom_image(obj.get("nomImage").toString());
                  float nbp = Float.parseFloat(obj.get("nbp").toString());
                          float nbr = Float.parseFloat(obj.get("nbr").toString());
                        task.setNbp((int) nbp);
                        task.setNbr((int) nbr);
                    task.setNomuser(obj.get("nomuser").toString());
                        listform.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listform;
    }

   
    
    public void udpateF(Formation ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/"+ta.getReferencef()+"/updatemob?titre="+ta.getTitre()+"&date="+ta.getDate()+"&domaine="+ta.getDomaine()+"&description="+ta.getDescription()+"&nbp="+ta.getNbp();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void DeleteF(float ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/deletemob/"+ta;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ajoutF(Formation f) {
        ConnectionRequest con = new ConnectionRequest();
        String name = Authentication.currentUser.getUsername();
        int idd=Authentication.currentUser.getId();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/newJ?titre="+f.getTitre()+"&date="+f.getDate()+"&domaine="+f.getDomaine()+"&description="+f.getDescription()+"&nom_image="+f.getNom_image()+"&nbp="+f.getNbp()+"&nomuser="+name+"&iduser="+idd;
        System.out.println(Url);

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 public void ajoutP(int idu, int idf) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/newJP/" +idu +"/"+idf;
           
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  public ArrayList<Participation> rechPart(int idu, int ide) 
  
  {
        ArrayList<Participation> listEvts = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIVPreFinal/web/app_dev.php/rechpart/" + idu + "/" + ide;
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> partic = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) partic.get("root");
                    for (Map<String, Object> obj : list) {
                        Participation ev = new Participation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        ev.setId((int) id);
                    
                        ev.setIdu((int)(double) obj.get("idu"));
                        ev.setIde((int) (double) obj.get("ide"));
                        System.out.println(ev);
                        listEvts.add(ev);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvts;

    }

   
   
}