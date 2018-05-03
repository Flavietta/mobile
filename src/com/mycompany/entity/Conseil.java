/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author Amine
 */
public class Conseil {
    private int refc;
    private String titre;
    private int iduser;
    private String nomuser;
    private String description;
    private String categorie;
    private String contenu;

    public Conseil() {
    }

    public Conseil(String titre, int iduser, String nomuser, String description, String categorie, String contenu) {
        this.titre = titre;
        this.iduser = iduser;
        this.nomuser = nomuser;
        this.description = description;
        this.categorie = categorie;
        this.contenu = contenu;
    }

    public Conseil(int refc, String titre, String description, String categorie, String contenu) {
        this.refc = refc;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.contenu = contenu;
    }

    public Conseil(String titre, int iduser, String nomuser, String description, String categorie, String contenu, int refc) {
        this.refc = refc;
        this.titre = titre;
        this.iduser = iduser;
        this.nomuser = nomuser;
        this.description = description;
        this.categorie = categorie;
        this.contenu = contenu;
    }

    public Conseil(int refc, String titre) {
        this.refc = refc;
        this.titre = titre;
    }
    

    public Conseil(int refc) {
        this.refc = refc;
    }

   

    public Conseil(String nomuser) {
        this.nomuser = nomuser;
    }

    public Conseil(String titre,String description, String contenu) {
        this.description = description;
        this.contenu = contenu;
        this.titre=titre;
    }

    

 

    public int getRefc() {
        return refc;
    }

    public void setRefc(int refc) {
        this.refc = refc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.refc;
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
        final Conseil other = (Conseil) obj;
        if (this.refc != other.refc) {
            return false;
        }
        return true;
    }

    public Conseil(String titre, String description, String categorie, String contenu) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "\n"+"Conseil{" +"\n"+ "* refc=" + refc + "\n"+"* titre=" + titre +"\n"+ "* iduser=" + iduser + "\n"+"* nomuser=" + nomuser + "\n"+"* description=" + description + "\n"+"* categorie=" + categorie + "\n"+"* contenu=" + contenu + '}';
    }

    
}