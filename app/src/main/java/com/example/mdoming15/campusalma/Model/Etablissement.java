package com.example.mdoming15.campusalma.Model;

import java.io.Serializable;

/**
 * Created by mdoming15 on 19/11/2015.
 */
public class Etablissement implements Serializable {

    private int id ;
    private String nom ;
    private String adresse ;
    private int type ;
    private int note ;
    private int favoris ;
    private String image ;

    public Etablissement(String nom, String adresse, int type, int note, int favoris) {
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.note = note;
        this.favoris = favoris;
        this.image = "vide" ;
    }

    public Etablissement(String nom, String adresse, int type, int note, int favoris, String image) {
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.note = note;
        this.favoris = favoris;
        this.image = image ;
    }

    public Etablissement(int id ,String nom, String adresse, int type, int note, int favoris, String image) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.note = note;
        this.favoris = favoris;
        this.image = image;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNote() {
        return note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNote(int note) {
        this.note = note;

    }

    public int getFavoris() {
        return favoris;
    }

    public void setFavoris(int favoris) {
        this.favoris = favoris;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
