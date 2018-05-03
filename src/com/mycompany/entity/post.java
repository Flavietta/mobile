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
public class post {
    private int id;
    private String description;
    private int id_conseil;
    private String nom_user;

    public post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_conseil() {
        return id_conseil;
    }

    public void setId_conseil(int id_conseil) {
        this.id_conseil = id_conseil;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public post(int id_conseil) {
        this.id_conseil = id_conseil;
    }

    @Override
    public String toString() {
        return "post{" + "id=" + id + ", description=" + description + ", id_conseil=" + id_conseil + ", nom_user=" + nom_user + '}';
    }

     

    public post(int id, String description, int id_conseil, String nom_user) {
        this.id = id;
        this.description = description;
        this.id_conseil = id_conseil;
        this.nom_user = nom_user;
    }

    public post(String description, int id_conseil, String nom_user) {
        this.description = description;
        this.id_conseil = id_conseil;
        this.nom_user = nom_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final post other = (post) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
