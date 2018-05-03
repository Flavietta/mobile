/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.mycompany.entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
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
public class UserController {

    public static List<User> all(){
        ArrayList<User> listUsers = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PIV1/web/app_dev.php/api/all/";
        con.setUrl(url);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(users);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
                    //System.out.println("LIST => " + list.toString());
                    //System.out.println("FIRST IN FIRST => " + Float.parseFloat(list.get(0).get("id").toString()));
                    for (Map<String, Object> obj : list) {
                        User user = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());
                        user.setId((int) id);
                        if (obj.get("confirmationToken")!=null)
                        user.setImage(obj.get("confirmationToken").toString());
                        user.setUsername(obj.get("username").toString());
                        user.setUsernameCanonical(obj.get("usernameCanonical").toString());
                        user.setEmail(obj.get("email").toString());
                        user.setEmailCanonical(obj.get("emailCanonical").toString());
                        user.setPassword(obj.get("password").toString());
                        user.setRole((List)obj.get("roles"));
                        listUsers.add(user);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
        }
    
    
    public static List<User> getUsersByUsername(String username ){
        List<User> list=new ArrayList<User>();
        for (User obj : all()) {
            if (obj.getUsername().equals(username) || obj.getUsernameCanonical().equals(username)) {
                list.add(obj);
            }

        }
        return list;
    }
    
     public static User getUsersById(int id ){
        List<User> list=new ArrayList<User>();
        for (User obj : all()) {
            if (obj.getId()==id ) {
                return obj;
            }

        }
        return null;
    }
     
     public static User getUsersByMail(String mail ){
        List<User> list=new ArrayList<User>();
        for (User obj : all()) {
            if (obj.getEmail().equals(mail) ) {
                return obj;
            }

        }
        return null;
    }
    
    public static void addUser(User user){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/add?name=" + user.getUsername() + "&email=" + user.getEmail() + "&password=" + user.getPassword();
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public static void updateUser(User user,String image){
        if (Authentication.currentUser!=null) {
        String filestack = "http://localhost/PIV1/web/app_dev.php/api/rec/imageupload/?image="+user.getImage();
        MultipartRequest request = new MultipartRequest() {
           protected void readResponse(InputStream input) throws IOException  {
          
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
             request.addData("file", image, "*/*");
            request.setFilename("file", "image.png");
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch(IOException err) {
            err.printStackTrace();
        }
            
            
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/update?id="+Authentication.currentUser.getId()+"&name=" + user.getUsername() + "&email=" + user.getEmail() + "&password=" + user.getPassword() + "&image=" + user.getImage();
        con.setUrl(Url);
       
        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    }
    
    public static void updateUser(User user){
            
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/update?id="+user.getId()+"&name=" + user.getUsername() + "&email=" + user.getEmail() + "&password=" + user.getPassword() + "&image=" + user.getImage();
        con.setUrl(Url);
       
        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }
    
    public static User getUser(Map<String,Object> obj){
        User user = new User();
        float id = Float.parseFloat(obj.get("id").toString());
        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());
        user.setId((int) id);
        user.setUsername(obj.get("username").toString());
        user.setUsernameCanonical(obj.get("usernameCanonical").toString());
        user.setEmail(obj.get("email").toString());
        user.setEmailCanonical(obj.get("emailCanonical").toString());
        user.setPassword(obj.get("password").toString());
        user.setRole((List)obj.get("roles"));
        return user;
    
    }
    
    
    public static int generateCode(){
        Random rand = new Random();
        int random = rand.nextInt(9999) + 1111;
        List<Integer> all = getAllCode();
        while (codeExists(random, all)) {            
            random = rand.nextInt(9999) + 1111;
        }
        System.out.println("Random " + random);
        return random ;
    }
    public static List<Integer> getAllCode(){
        ArrayList<Integer> listRecs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIV1/web/app_dev.php/api/getAllCode/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> recs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(users);
                    //System.out.println(tasks);
                    List<Integer> list = (List<Integer>) recs.get("root");
                    //System.out.println("LIST => " + list.toString());
                    //System.out.println("FIRST IN FIRST => " + Float.parseFloat(list.get(0).get("id").toString()));
                    for (Object obj : list) {
                        
                        //float id = Float.parseFloat(obj.get("id").toString());
                        //System.out.println("id = " + obj.get("id").toString() + " usename = " + obj.get("name").toString());
                        if (obj!=null){
                            float code = Float.parseFloat(obj.toString());

                            listRecs.add(Integer.valueOf((int)code+""));

                        }
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecs;
        
    }
    
    private static boolean codeExists(int code , List<Integer> all){
        for (Integer integer : all) {
            if (integer!=null) {
                if (integer==code)
                    return true;    
            }
            
        }
        return false;
    }
    
    public static int getCode(int userId){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIV1/web/app_dev.php/api/getCode/?id="+userId);
        List<Integer> code = new ArrayList<>();
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> recs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(users);
                    //System.out.println(tasks);
                    if (true) {
                        
                    }
                    code.add(Integer.valueOf(new String(con.getResponseData())));
                    //System.out.println("LIST => " + list.toString());
                    //System.out.println("FIRST IN FIRST => " + Float.parseFloat(list.get(0).get("id").toString()));
                    
                } catch (IOException ex) {
                } catch(NullPointerException ex){
                    code.add(1111);
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return code.get(0);
    }
    public static void setCode(int userId){ 
    ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PIV1/web/app_dev.php/api/setCode/?id=" + userId + "&code=" + generateCode();
        con.setUrl(Url);

        System.out.println("");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
