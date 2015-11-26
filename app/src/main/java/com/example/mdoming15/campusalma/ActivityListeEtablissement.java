package com.example.mdoming15.campusalma;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Etablissement;

public class ActivityListeEtablissement extends AppCompatActivity {


    DAO daoManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etablissement);

        daoManager = new DAO(this);
        daoManager.open();

        ListView listeView = (ListView) findViewById(R.id.etablissements_ListView);
        Intent intent = getIntent();

        final int  methode = intent.getExtras().getInt("Methode");

        Cursor cursor ;

        switch (methode){
            case 0: cursor = daoManager.getAllSnack();
                    //daoManager.close();
                break;
            case 1: cursor = daoManager.getAllRestaurant();
                    //daoManager.close();
                break;
            case 2: cursor = daoManager.getAllFavoris();
                    //daoManager.close();
                break;
            default :   cursor = daoManager.getAllEtablissement();
                        //daoManager.close();
                break;
        }

        EtablissementCursorAdapter adapter = new EtablissementCursorAdapter(this,cursor,false);
        listeView.setAdapter(adapter);
        listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor curs = (Cursor) parent.getItemAtPosition(position);
                Etablissement etablissement = DAO.getEtablissementFromCursor(curs);

                Intent intent = new Intent(ActivityListeEtablissement.this, Details_etablissement.class);
                intent.putExtra("Etablissement", etablissement);
                intent.putExtra("Methode", methode);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_liste_etablissement, menu);
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
