package com.example.mdoming15.campusalma;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Etablissement;

public class MainMenu extends AppCompatActivity {
    Cursor cursor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DAO dao = new DAO(getApplicationContext());
        dao.open();

        cursor = dao.getAllEtablissement();

        Button snackButton ;
        snackButton = (Button) findViewById(R.id.buttonSnack);
        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoListe = new Intent(MainMenu.this, ActivityListeEtablissement.class);
                infoListe.putExtra("Methode",0);
                startActivity(infoListe);
            }
        });

        Button restoButton ;
        restoButton = (Button) findViewById(R.id.buttonResto);
        restoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoListe = new Intent(MainMenu.this, ActivityListeEtablissement.class);
                infoListe.putExtra("Methode",1);
                startActivity(infoListe);
            }
        });

        Button favorisButton ;
        favorisButton = (Button) findViewById(R.id.buttonFavoris);
        favorisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoListe = new Intent(MainMenu.this, ActivityListeEtablissement.class);
                infoListe.putExtra("Methode",2);
                startActivity(infoListe);
            }
        });













        //TEST DB
        /*
        Cursor cursor = dao.getAllFavoris();
        int test = cursor.getCount() ;

        while (cursor.moveToNext()) {
            int i = 1 ;

            Etablissement etablissement = DAO.getEtablissementFromCursor(cursor);
            Log.i("Num Etablissement : " , ""+i);
            Log.i("Nom  " , etablissement.getNom());
            Log.i("Adresse  " , etablissement.getAdresse());
            Log.i("Type (int) : " , ""+etablissement.getType());
            Log.i("Favoris (int) : " , ""+etablissement.getFavoris());
            Log.i("Note (int) : " , ""+etablissement.getNote());
            Log.i("Image  " , etablissement.getImage());

            i++ ;

        }

        Log.i("TEST", "nombre dans cursor  : "+ test );

        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
