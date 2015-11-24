package com.example.mdoming15.campusalma;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Etablissement;

import static com.example.mdoming15.campusalma.R.drawable.resto_test;

public class Details_etablissement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_etablissement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DAO dao = new DAO(this);
        dao.open();

        Intent intent = getIntent();
        final Etablissement etablissement = (Etablissement) intent.getSerializableExtra("Etablissement");
        ImageView imageView = (ImageView) findViewById(R.id.detail_image_etablissement);
        imageView.setImageResource(R.drawable.resto_test);

        TextView nomE = (TextView) findViewById(R.id.detail_nom_etablessement);
        nomE.setText(etablissement.getNom());
        TextView idE = (TextView) findViewById(R.id.detail_id_etablessement);
        idE.setText(Integer.toString(etablissement.getId()));
        TextView adresseE = (TextView) findViewById(R.id.detail_adresse_etablessement);
        adresseE.setText(etablissement.getAdresse());
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(etablissement.getNote());
        ratingBar.setDrawingCacheBackgroundColor(Color.RED);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {


            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("TEST ratingBARRATING = ", Float.toString(rating));

                etablissement.setNote(Math.round(rating));
                dao.updateEtablissement(etablissement);
                Log.i("note changer = ", Integer.toString(etablissement.getId()));
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
