package com.example.mdoming15.campusalma.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mdoming15 on 19/11/2015.
 */
public class DAO {


    //protected final static int VERSION = 1 ;
    //protected final static String NOM = "CampusAlma.db";

    protected DBHandler dbHandler = null;
    protected SQLiteDatabase mDB = null;



    public DAO(Context context ){

        dbHandler = new DBHandler(context);

    }

    public void open(){     //SQLiteDatabase ---> return

        try {
            mDB = dbHandler.getWritableDatabase();
        } catch (Exception e) {
            mDB = dbHandler.getReadableDatabase();
        }
    }

    public static Etablissement getEtablissementFromCursor(Cursor cursor){

        if (cursor == null || cursor.getCount() == 0){
            return null;
        }

        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        String nom = cursor.getString(cursor.getColumnIndex("nom"));
        String adresse = cursor.getString(cursor.getColumnIndex("adresse"));
        int type = cursor.getInt(cursor.getColumnIndex("type"));
        int note = cursor.getInt(cursor.getColumnIndex("note"));
        String image = cursor.getString(cursor.getColumnIndex("image"));
        int favoris = cursor.getInt(cursor.getColumnIndex("favoris"));

        return new Etablissement(id ,nom , adresse, type, note, favoris, image);
    }

    public Cursor getAllEtablissement(){

        return mDB.rawQuery("select * from etablissements", null);
    }

    public Cursor getAllSnack(){
        return mDB.rawQuery("SELECT * FROM etablissements WHERE type = 1 ", null );
    }

    public Cursor getAllRestaurant(){

        return mDB.rawQuery("SELECT * FROM etablissements WHERE type = 0 ", null );
    }

    public Cursor getAllFavoris(){

        return mDB.rawQuery("SELECT * FROM etablissements WHERE favoris = 1 ", null );
    }





    public void close(){
        mDB.close();
    }

    public void insertEtablissement(Etablissement etablissement){

        ContentValues valeurs = new ContentValues();
        valeurs.put("nom", etablissement.getNom());
        valeurs.put("adresse", etablissement.getAdresse());
        valeurs.put("note", etablissement.getNote());
        valeurs.put("type", etablissement.getType());
        valeurs.put("favoris", etablissement.getFavoris());
        valeurs.put("image", etablissement.getImage());

        mDB.insert("etablissements", null, valeurs);
    }

    public void updateEtablissement(Etablissement etablissement){
        ContentValues valeurs = new ContentValues();
        valeurs.put("nom", etablissement.getNom());
        valeurs.put("type", etablissement.getType());
        valeurs.put("adresse",etablissement.getAdresse());
        valeurs.put("note", etablissement.getNote());
        valeurs.put("image", etablissement.getImage());
        valeurs.put("favoris", etablissement.getFavoris());

        mDB.update("etablissements", valeurs
                , "nom" + "='" + etablissement.getNom() + "'", null);
    }
}
