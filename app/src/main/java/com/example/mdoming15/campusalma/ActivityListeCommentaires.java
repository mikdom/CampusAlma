package com.example.mdoming15.campusalma;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mdoming15.campusalma.Model.Commentaire;
import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.DialogAddCommentaire;

public class ActivityListeCommentaires extends AppCompatActivity {

    DAO daoManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaires);


        daoManager = new DAO(this);
        daoManager.open();

        //daoManager.insertCommentaire(new Commentaire()
        //daoManager.insertCommentaire(new Commentaire("Ceci n'est pas un commentaire ", 4));

        final ListView listeViewCommentaire = (ListView) findViewById(R.id.commentaires_ListView);
        listeViewCommentaire.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Intent getIntent = getIntent();
        int etablissement_id = getIntent.getExtras().getInt("Etablissement_id");

        final Cursor cursor = daoManager.getAllCommentairesFromEtablissement(etablissement_id);

        final CommentaireCursorAdapter adapter = new CommentaireCursorAdapter(this,cursor,false);

        listeViewCommentaire.setAdapter(adapter);

/*
        listeViewCommentaire.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Je suis bien rentré", "YEAH");
                CommentaireCursorAdapter commentaireCursorAdapter = (CommentaireCursorAdapter) listeViewCommentaire.getAdapter();
                Cursor cursor1 = (Cursor) listeViewCommentaire.getItemAtPosition(position);

                CheckedTextView checkedTextView = (CheckedTextView) parent.getSelectedView();
                checkedTextView.setChecked(true);

                listeViewCommentaire.setItemChecked(position,true);

                //commentaireCursorAdapter.checkedView(view,cursor1,view.getId());


        }
    });
*/
        SparseBooleanArray sparseBooleanArray = listeViewCommentaire.getCheckedItemPositions();
        Log.i("Le sparseBoolean :",sparseBooleanArray.toString());


        Log.i("La Taille sparseBool : ",""+sparseBooleanArray.size());

        Cursor cursor1 = (Cursor) listeViewCommentaire.getItemAtPosition(0);
        Commentaire commentaire = DAO.getCommentaireFromCursor(cursor1);
        Log.i("Le texte commentaire :", commentaire.getTexte());


        Button buttonAdd = (Button)findViewById(R.id.buttonAdd_liste_commentaire);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddCommentaire dialogAddCommentaire = new DialogAddCommentaire();
                dialogAddCommentaire.show(getSupportFragmentManager(),"Ajouter");
            }
        });

        Button buttonDel = (Button) findViewById(R.id.buttonDelete_liste_commentaire);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAILLE arraylist : ", "" + adapter.getListeCommDel().size());
                //adapter.swapCursor(cursor_update);
            }
        });

    }

}
