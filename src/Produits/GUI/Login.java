/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.GUI;

import com.mycompany.services.Authentication;
import Produits.entities.User1;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.AfficheVisitor;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SideMenuBaseForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yahya
 */
public class Login {
    
    Form Login;
    TextField username;
    TextField pass;
    Button btnok;
    public static String usernamPUB = Authentication.currentUser.getUsername() ;
    public static int useridPUB = Authentication.currentUser.getId() ;
    private Resources theme;

 
        
    
      public Login() {
          
          theme = UIManager.initFirstTheme("/theme");
        Login = new Form();
        username = new TextField("", "Login",25,TextField.ANY);
        username.getStyle().setFgColor(0x3A2A7D  );
        pass = new TextField("", "PassWord", 25, TextField.PASSWORD) ;
        pass.getStyle().setFgColor(0x3A2A7D  );
      pass.setConstraint(TextField.PASSWORD);
        btnok=new Button("Affichage");
       Login.getStyle().set3DText(true, true);
        Login.add(username);
        
        Login.add(pass);
       Login.add(btnok) ;
       
       btnok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                
                
                
               
                checkUser();
             
                checkUserID();
                usernamPUB= username.getText();
                System.out.println(usernamPUB);
                System.out.println(useridPUB);
                       
                       }
        });
      }           

      
      
      
       public void checkUser() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8181/PIV1/web/app_dev.php/user");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                     
                        boolean test = false ; 
                       
                    
                    for (Map<String, Object> obj : list) {
                        User1 task = new User1() ;
                        
                        if (username.getText().equals(obj.get("username").toString()))
                        { 
                            
                          test = true ;
                       new AfficheVisitor(theme).show();
                        
                        
                        }
                       
                     }
                    if (test== false){
                    
                      new Dialog().show("Alerte","verifiez l un de vos infos svp ", "Ok", "Cancel");
                   
                    }      
                       
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
     
       public void checkUserID() {
           
          
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8181/PIV1/web/app_dev.php/user");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                     
                 
                    
                    for (Map<String, Object> obj : list) {
                        User1 task = new User1() ;
                        
                        if ( (obj.get("username").toString()).equalsIgnoreCase(username.getText()))
                        {
                         float  id = Float.parseFloat(obj.get("id").toString());
                        
                        
               useridPUB=(int) id ;
                   
                     
                        }
                   
                    
                    }
                       
                } catch (IOException ex) {
                }
 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
      
      
     
    public Form getFLogin() {
        return Login;
    }

    public void setFLogin(Form f) {
        this.Login = Login;
    }

    public TextField getusername() {
        return username;
    }

    public void setTnom(TextField tnom) {
        this.username = username;
    }

      
      
      
      
     ///////////////////////////////////////////////////////////////////////////
    
    
}
