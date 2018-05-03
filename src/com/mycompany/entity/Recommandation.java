/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.List;

/**
 *
 * @author FALAVIO
 */
public class Recommandation {
    private User owner;
    private int id ;
    private String description;
    private String file;
    private String title;
    private float rate;
    private List<Rate> rateList;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRate() {
        return rate;
    }


    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        if (!rateList.isEmpty()) {
             int i = 0 ; 
            int sum = 0;
            for (Rate rate1 : rateList) {
                sum+=rate1.getRate();
                i++;
            }
            this.rate = sum/i;
        }else
            this.rate=0;
        this.rateList = rateList;

    }

    @Override
    public String toString() {
        return "Recommandation{" + "owner=" + owner + ", id=" + id + ", description=" + description + ", file=" + file + ", title=" + title + ", rate=" + rate + ", rateList=" + rateList + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Recommandation other = (Recommandation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
