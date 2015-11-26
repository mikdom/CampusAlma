package com.example.mdoming15.campusalma.Model;

/**
 * Created by Wiiki on 25-11-15.
 */
public class Commentaire {

    private int id ;
    //private String date;
    private String texte ;
    private int id_etablissement ;

    public Commentaire(String texte, int id_etablissement){
        this.texte = texte ;
        this.id_etablissement = id_etablissement ;
    }

    public Commentaire(int id , String texte, int id_etablissement){
        this.id = id;
        this.texte = texte ;
        this.id_etablissement = id_etablissement ;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
