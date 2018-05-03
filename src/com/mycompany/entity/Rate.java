/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author FALAVIO
 */
public class Rate {
    private int rate;
    private Recommandation rec;
    private User votedUser;
    private int id ;
       
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rate other = (Rate) obj;
        if (!this.rec.equals(other.rec)) {
            return false;
        }
        if (!this.votedUser.equals( other.votedUser)) {
            return false;
        }
        return true;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if (rate>5 ) {
            this.rate = 5 ;
        }else if (rate<0) {
            this.rate = 0;
        }
         this.rate = rate;
    }

    public Recommandation getRec() {
        return rec;
    }

    public void setRec(Recommandation rec) {
        this.rec = rec;
    }

    public User getVotedUser() {
        return votedUser;
    }

    public void setVotedUser(User votedUser) {
        this.votedUser = votedUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rate{" + "rate=" + rate + ", rec=" + rec + ", votedUser=" + votedUser + ", id=" + id + '}';
    }
    
    
}
