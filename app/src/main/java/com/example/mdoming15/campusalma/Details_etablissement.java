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

import com.example.mdoming15.campusalma.Model.Commentaire;
import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Etablissement;

import static com.example.mdoming15.campusalma.R.drawable.resto_test;

public class Details_etablissement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_etablissement);

        final DAO dao = new DAO(this);
        dao.open();

        Intent intent = getIntent();
        final Etablissement etablissement = (Etablissement) intent.getSerializableExtra("Etablissement");

        ImageView imageView = (ImageView) findViewById(R.id.detail_image_etablissement);
        imageView.setImageResource(R.drawable.resto_test);

        final ImageView imageLike = (ImageView) findViewById(R.id.detail_image_like);
        if(etablissement.getFavoris()==0){
            imageLike.setImageResource(R.drawable.icon_coeur_nb);
        }else{
            imageLike.setImageResource(R.drawable.icon_coeur_red);
        }

        imageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etablissement.getFavoris()==0){
                    etablissement.setFavoris(1);
                    imageLike.setImageResource(R.drawable.icon_coeur_red);
                    dao.updateEtablissement(etablissement);
                }else{
                    etablissement.setFavoris(0);
                    imageLike.setImageResource(R.drawable.icon_coeur_nb);
                    dao.updateEtablissement(etablissement);
                }
            }
        });

        TextView nomE = (TextView) findViewById(R.id.detail_nom_etablessement);
        nomE.setText(etablissement.getNom());

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

        TextView adresseE = (TextView) findViewById(R.id.detail_adresse_etablessement);
        //adresseE.setText(etablissement.getAdresse());
        adresseE.setText(" Clos Chapelle-aux-Champs 43, 1200 Bruxelles");

        ImageView imageGMap = (ImageView) findViewById(R.id.detail_image_googleMap);
        imageGMap.setImageResource(R.drawable.google_maps);

        TextView menuE = (TextView) findViewById(R.id.detail_menu_etablissement);
        menuE.setText("Menu");

        TextView commentaireE = (TextView) findViewById(R.id.detail_commentaire_etablissement);
        commentaireE.setText("Commentaires");
        commentaireE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCommentaire = new Intent(Details_etablissement.this, Commentaires.class);
                intentCommentaire.putExtra("Etablissement_id", etablissement.getId());
                startActivity(intentCommentaire);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_etablissement_menu, menu);
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
        Intent result = getIntent();
        Intent intent = new Intent(Details_etablissement.this,ActivityListeEtablissement.class);
        intent.putExtra("Methode",result.getExtras().getInt("Methode"));
        startActivity(intent);

        return true ;
    }

}
