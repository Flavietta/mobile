/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author majid
 */
public class Notif_soin {
     private int id_notif;
    private String titre;
    private String desc;
    private int id_soins;
    private String nom_user_sender;
    private int id_user_sender;
    private int id_user_rec;
    
    
    public Notif_soin(){
    
    }
    
    
    public Notif_soin(String titre,int id_soins,String nom_user_sender ,int id_user_sender, int id_user_rec,String desc) {
        this.titre = titre;
        this.id_soins = id_soins;
        this.id_user_sender = id_user_sender;
        this.id_user_rec = id_user_rec;
        this.nom_user_sender=nom_user_sender;
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    public String getNom_user_sender() {
        return nom_user_sender;
    }

    public void setNom_user_sender(String nom_user_sender) {
        this.nom_user_sender = nom_user_sender;
    }

    public int getId_notif() {
        return id_notif;
    }

    public void setId_notif(int id_notif) {
        this.id_notif = id_notif;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId_soins() {
        return id_soins;
    }

    public void setId_soins(int id_soins) {
        this.id_soins = id_soins;
    }

    public int getId_user_sender() {
        return id_user_sender;
    }

    public void setId_user_sender(int id_user_sender) {
        this.id_user_sender = id_user_sender;
    }

    public int getId_user_rec() {
        return id_user_rec;
    }

    public void setId_user_rec(int id_user_rec) {
        this.id_user_rec = id_user_rec;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id_notif;
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
        final Notif_soin other = (Notif_soin) obj;
        if (this.id_notif != other.id_notif) {
            return false;
        }
        return true;
    }

   /* @Override
    public String toString() {
        return "Notif_soin{" + "id_notif=" + id_notif + ", titre=" + titre + ", id_soins=" + id_soins + ", id_user_sender=" + id_user_sender + ", id_user_rec=" + id_user_rec + '}';
    }*/

    @Override
    public String toString() {
        return "Notif_soin{" + "id_notif=" + id_notif + ", titre=" + titre + ", desc=" + desc + ", id_soins=" + id_soins + ", nom_user_sender=" + nom_user_sender + ", id_user_sender=" + id_user_sender + ", id_user_rec=" + id_user_rec + '}';
    }
    
    
}
