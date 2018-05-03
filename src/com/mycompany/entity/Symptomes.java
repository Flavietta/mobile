/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;


public class Symptomes {
    private int id;
    private String symptome;
    private int id_symp;

    public Symptomes() {
    }
    
    
    public Symptomes(int id, String symptome,int idsymp){
        this.id=id;
        this.symptome=symptome;
        this.id_symp=idsymp;
        
}
    public Symptomes(String symptome){
        this.symptome=symptome;
    }
    public Symptomes(int id){
        this.id=id; 
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public int getId_symp() {
        return id_symp;
    }

    public void setId_symp(int id_symp) {
        this.id_symp = id_symp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
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
        final Symptomes other = (Symptomes) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Symptomes{" + "id=" + id + ", symptome=" + symptome + ", id_symp=" + id_symp + '}';
    }
    
}

