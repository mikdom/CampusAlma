package com.example.mdoming15.campusalma.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.database.sqlite.SQLiteDatabase.*;

/**
 * Created by Wiiki on 17-11-15.
 */
public class DBHandler extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CampusAlma.db";

    //table Etablissements
    public static final String TABLE_ETABLISSEMENTS = "etablissements";
    private static final String COL_ID_ETABLISSEMENT = "_id";
    private static final String COL_NOM = "nom";
    private static final String COL_ADRESSE = "adresse";
    private static final String COL_TYPE = "type";
    private static final String COL_IMAGE = "image";
    private static final String COL_NOTE = "note";
    private static final String COL_FAVORIS = "favoris";


    //table Commentaires
    private static final String TABLE_COMMENTAIRES = "commentaires";
    private static final String COL_ID_COMMENTAIRE = "_id";
    private static final String COL_TEXTE = "texte";
    private static final String COL_ETABLISSEMENT = "etablissement";

    private static final String CREATE_TABLE_ETABLISSEMENTS = "CREATE TABLE " + TABLE_ETABLISSEMENTS + " ("
            + COL_ID_ETABLISSEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOM + " TEXT NOT NULL, "
            + COL_ADRESSE + " TEXT NOT NULL, "
            + COL_TYPE + " INTEGER NOT NULL, "
            + COL_IMAGE + " TEXT ,"
            + COL_NOTE + " INTEGER , "
            + COL_FAVORIS + " INTEGER DEFAULT 0 );";

    private static final String CREATE_TABLE_COMMENTAIRES = "CREATE TABLE " + TABLE_COMMENTAIRES + " ("
            + COL_ID_COMMENTAIRE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TEXTE + " TEXT NOT NULL, "
            + COL_ETABLISSEMENT + " INTEGER ,"
            + "FOREIGN KEY(etablissement) REFERENCES etablissements(_id));";


    public DBHandler( Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ETABLISSEMENTS);
        db.execSQL(CREATE_TABLE_COMMENTAIRES);
        peuplement(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETABLISSEMENTS);     //";"
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTAIRES);
        onCreate(db);
    }


    public void insertEtablissement(Etablissement etablissement, SQLiteDatabase db){

        ContentValues valeurs = new ContentValues();
        valeurs.put("nom", etablissement.getNom());
        valeurs.put("adresse", etablissement.getAdresse());
        valeurs.put("type", etablissement.getType());
        valeurs.put("image", etablissement.getImage());
        valeurs.put("note", etablissement.getNote());
        valeurs.put("favoris", etablissement.getFavoris());

        db.insert("etablissements", null, valeurs);
    }


    private void peuplement(SQLiteDatabase db){


        insertEtablissement(new Etablissement("RestoBrezilien", "rue brezil", 0, 0, 0), db);
        insertEtablissement(new Etablissement("RestoEspagna", "rue QueRicoDePanRico", 0, 0, 0), db);
        insertEtablissement(new Etablissement("RestoMexicain", "rue mexico", 0, 0, 1), db);
        insertEtablissement(new Etablissement("SnackFrite", "rue fritemayo", 1, 0, 0), db);
        insertEtablissement(new Etablissement("SnackSandwish", "rue martino", 1, 0, 0), db);
        insertEtablissement(new Etablissement("SnackPizza", "rue des 100 Anchois", 1, 0, 1), db);

    }

}
