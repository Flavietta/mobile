/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.entities;



/**
 *
 * @author yahya
 */
public class wishlist {
    
    private int wanter ; 
    private int panier ; 
    private int idProduitPanier ; 
    private int idOwner ; 
    private  String  Nomproduit ; 
    private  String  wantername ; 
   private  String  owner ; 

    public wishlist() {
    }

    public wishlist(int wanter, int panier, int idProduitPanier, String wantername) {
        this.wanter = wanter;
        this.panier = panier;
        this.idProduitPanier = idProduitPanier;
        this.wantername = wantername;
    }

    public wishlist(int wanter, int idProduitPanier, int idOwner, String Nomproduit, String wantername, String owner) {
        this.wanter = wanter;
        this.idProduitPanier = idProduitPanier;
        this.idOwner = idOwner;
        this.Nomproduit = Nomproduit;
        this.wantername = wantername;
        this.owner = owner;
    }

    public wishlist(int wanter, int panier, int idProduitPanier, int idOwner, String Nomproduit, String wantername, String owner) {
        this.wanter = wanter;
        this.panier = panier;
        this.idProduitPanier = idProduitPanier;
        this.idOwner = idOwner;
        this.Nomproduit = Nomproduit;
        this.wantername = wantername;
        this.owner = owner;
    }

    public wishlist(int panier) {
          this.panier = panier;
    }

    public int getWanter() {
        return wanter;
    }

    public void setWanter(int wanter) {
        this.wanter = wanter;
    }

    public int getPanier() {
        return panier;
    }

    public void setPanier(int panier) {
        this.panier = panier;
    }

    public int getIdProduitPanier() {
        return idProduitPanier;
    }

    public void setIdProduitPanier(int idProduitPanier) {
        this.idProduitPanier = idProduitPanier;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getNomproduit() {
        return Nomproduit;
    }

    public void setNomproduit(String Nomproduit) {
        this.Nomproduit = Nomproduit;
    }

    public String getWantername() {
        return wantername;
    }

    public void setWantername(String wantername) {
        this.wantername = wantername;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "wishlist{" + "wanter=" + wanter + ", panier=" + panier + ", idProduitPanier=" + idProduitPanier + ", idOwner=" + idOwner + ", Nomproduit=" + Nomproduit + ", wantername=" + wantername + ", owner=" + owner + '}';
    }

    
    
}
