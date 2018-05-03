/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.entities;



/**
 *
 * @author yahya
 */
public class Produits {
    
    
    private int id ; 
    private  String  nomp  ; 
    private  String  username ; 
    private  int  userID ; 
    private  String  NomImage ;
     private  String  description ;
     private  String  etat ;
     private float longitude  ; 
     private float latitude ; 
      private String Ville ;

    public Produits(int id) {
        this.id = id;
    }

    public Produits(int id, String nomp, String username , int userID) {
        this.id = id;
        this.nomp = nomp;
        this.username = username;
        this.userID = userID;
    }



    public Produits(int id, String nomp, String description, String etat) {
        this.id = id;
        this.nomp = nomp;
        this.description = description;
        this.etat = etat;
    }

    public Produits(String nomp, String description, String etat) {
        this.nomp = nomp;
        this.description = description;
        this.etat = etat;
    }

    public Produits(String nomp, String NomImage, String description, String etat , float longitude ,  float latitude , String Ville  ) {
        this.nomp = nomp;
        this.NomImage = NomImage;
        this.description = description;
        this.etat = etat;
        this.longitude = longitude ;
        this.latitude = latitude ; 
        this.Ville= Ville ; 
    }

    public Produits(String nomp, String username, int userID, String NomImage, String description, String etat) {
        this.nomp = nomp;
        this.username = username;
        this.userID = userID;
        this.NomImage = NomImage;
        this.description = description;
        this.etat = etat;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    
    
    
    public Produits() {
    }

    
    
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
     
     



    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomImage() {
        return NomImage;
    }

    public void setNomImage(String NomImage) {
        this.NomImage = NomImage;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    @Override
    public String toString() {
        return "Produits{" + "id=" + id + ", nomp=" + nomp + ", username=" + username + ", userID=" + userID + ", NomImage=" + NomImage + ", description=" + description + ", etat=" + etat + ", longitude=" + longitude + ", latitude=" + latitude + ", Ville=" + Ville + '}';
    }

    
    
    
}
