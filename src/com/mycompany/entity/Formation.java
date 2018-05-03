/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import com.codename1.l10n.SimpleDateFormat;

/**
 *
 * @author user
 */
public class Formation {
    private Integer referencef;
    private String titre;
    private String date;
    private String domaine;
    private String description;
     private String nomuser;
     private int nbr;
    private String nom_image;
    private int nbp;

    public Formation() {
    }

    public Formation(String titre, String date, String domaine, String description, String nom_image, int nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nom_image = nom_image;
        this.nbp = nbp;
    }

    public Formation(String titre, String date, String domaine, String description, String nomuser, String nom_image, int nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nomuser = nomuser;
        this.nom_image = nom_image;
        this.nbp = nbp;
    }
    

    public Formation(String titre, String date, String domaine, String description, Integer nbr, String nom_image) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nbr = nbr;
        this.nom_image = nom_image;
    }
    

   /* public Formation(String titre, String date, String domaine, String description, String nomuser, String nom_image, Integer nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nomuser = nomuser;
        this.nom_image = nom_image;
        this.nbp = nbp;
    }*/

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Formation(String nomuser) {
        this.nomuser = nomuser;
    }

    public Formation(Integer referencef, String titre, String date, String domaine, String description, Integer nbr, Integer nbp) {
        this.referencef = referencef;
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;

        this.nbr = nbr;
        this.nbp = nbp;
    }

    public Formation(String titre, String date, String domaine, String description, Integer nbr, Integer nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nbr = nbr;
        this.nbp = nbp;
    }

    public Integer getNbr() {
        return nbr;
    }

    public void setNbr(Integer nbr) {
        this.nbr = nbr;
    }

    public Formation(Integer referencef, String titre, String domaine, String description) {
        this.referencef = referencef;
        this.titre = titre;
        this.domaine = domaine;
        this.description = description;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    public Formation(String titre, String date, String domaine, String description, String nomuser, Integer nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
       
        this.nbp = nbp;
    
    }

    public Formation(String titre, String date, String domaine, String description, String nomuser, Integer nbr, String nom_image, Integer nbp) {
        this.titre = titre;
        this.date = date;
        this.domaine = domaine;
        this.description = description;
        this.nomuser = nomuser;
        this.nbr = nbr;
        this.nom_image = nom_image;
        this.nbp = nbp;
    }
    

    public Formation(String titre, String date, String domaine, String description, Integer nbp) {
        this.titre = titre;  
      this.domaine = domaine;
        this.description = description;
        this.date = date;
        this.nbp = nbp;
    }


  
    public Formation(Integer referencef, String titre, String date , String domaine,  String description, Integer nbp) {
        this.referencef = referencef;
        this.titre = titre;
        this.date=date;
        this.domaine = domaine;
        this.description = description;
        this.nbp = nbp;
    }

    public Formation(Integer referencef) {
        this.referencef = referencef;
    }

  
   
    public int getReferencef() {
        return referencef;
    }

    public void setReferencef(int referencef) {
        this.referencef = referencef;
    }

    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    @Override
    public String toString() {
        return "Formation{" + "titre=" + titre + ", date=" + date + ", domaine=" + domaine + ", description=" + description + ", nom_image=" + nom_image + ", nbp=" + nbp + '}';
    }

    

    
}
