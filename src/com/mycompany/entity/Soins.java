/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majid
 */
public class Soins {
    private int refs;
    private String titre;
    private String Description;
    private int userID;
    private String userName;
    private List<Symptomes>sympsoms ;
    
    public Soins (int refs,String titre,String Description,int userID,String userName){
    
    this.refs=refs;
    this.titre=titre;
    this.Description=Description;
    this.userID=userID;
    this.userName=userName;
    
    }
    
    public Soins (String titre,String Description,int userID,String userName){
    
    
    this.titre=titre;
    this.Description=Description;
    this.userID=userID;
    this.userName=userName;
    
    }
    
     public Soins (int refs,String titre){
    
    this.refs=refs;
    this.titre=titre;
    }

    public Soins() {
        sympsoms =new ArrayList<Symptomes>();
    }
     

    public int getRefs() {
        return refs;
    }

    public void setRefs(int refs) {
        this.refs = refs;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.refs;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Soins other = (Soins) obj;
        if (this.refs != other.refs) {
            return false;
        }
        return true;
    }

    public List<Symptomes> getSympsoms() {
        return sympsoms;
    }

    public void setSympsoms(List<Symptomes> sympsoms) {
        for(Symptomes s:sympsoms){
            this.sympsoms.add(s);
        }
    }
    public void addSymp(Symptomes s){
        this.sympsoms.add(s);
        }
    public void delSymp(Symptomes s ){
    this.sympsoms.remove(s);
    }

    

    @Override
    public String toString() {
        return "Soins{" + "refs=" + refs + ", titre=" + titre + ", Description=" + Description + ", userID=" + userID + ", userName=" + userName + '}';
    }
     
     
     
     


    
}

