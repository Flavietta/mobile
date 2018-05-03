/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author user
 */
public class Participation {
    private int id;
    private int idf;
    private int iduser;
    private int idu;
    private int ide;
    

    public Participation() {
    }

    public Participation(int id, int idf, int iduser) {
        this.id = id;
        this.idf = idf;
        this.iduser = iduser;
    }

    public Participation(int id, int idf, int iduser, int idu, int ide) {
        this.id = id;
        this.idf = idf;
        this.iduser = iduser;
        this.idu = idu;
        this.ide = ide;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", idf=" + idf + ", iduser=" + iduser + '}';
    }
    
    
}
