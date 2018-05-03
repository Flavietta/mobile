/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.util.Date;
import java.util.List;



/**
 *
 * @author FALAVIO
 */
public class User {
    private int id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private int enable;
    private String password;
    private Date lastLogin;
    private List role;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private List<Recommandation> recs; 

    public List<Recommandation> getRecs() {
        return recs;
    }

    public void setRecs(List<Recommandation> recs) {
        this.recs = recs;
    }
    
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List getRole() {
        return role;
    }

    public void setRole(List role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", lastLogin=" + lastLogin + ", role=" + role + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!this.username.equals(other.username)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
    
    
}
