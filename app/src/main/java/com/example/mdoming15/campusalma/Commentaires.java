package com.example.mdoming15.campusalma;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.mdoming15.campusalma.Model.Commentaire;
import com.example.mdoming15.campusalma.Model.DAO;

public class Commentaires extends AppCompatActivity {

    DAO daoManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaires);


        daoManager = new DAO(this);
        daoManager.open();

        //daoManager.insertCommentaire(new Commentaire()
        //daoManager.insertCommentaire(new Commentaire("Ceci n'est pas un commentaire ", 4));

        ListView listeViewCommentaire = (ListView) findViewById(R.id.commentaires_ListView);
        Intent getIntent = getIntent();
        int etablissement_id = getIntent.getExtras().getInt("Etablissement_id");

        Cursor cursor = daoManager.getAllCommentairesFromEtablissement(etablissement_id);

        Log.i("TEST CURSOR", "valeur : "+ cursor.getCount());
        Log.i("TEST INTENT", "valeur : "+ getIntent.getExtras().getInt("Etablissement_id"));


        while (cursor.moveToNext()) {
            int i = 1 ;

            Commentaire commentaire = DAO.getCommentaireFromCursor(cursor);
            Log.i("Num commentaire : " , ""+i);
            Log.i("id commentaire :  " , ""+commentaire.getId());
            Log.i("texte commentaire  " , commentaire.getTexte());

            i++ ;

        }



        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.layout_item_commentaire_listeview,cursor,new String[]{"texte"},new int[]{R.id.detail_commentaire_etablissement});

        listeViewCommentaire.setAdapter(adapter);


    }

}
