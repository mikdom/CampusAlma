package com.example.mdoming15.campusalma;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Commentaire;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Wiiki on 26-11-15.
 */
public class CommentaireCursorAdapter extends CursorAdapter {

    private ArrayList<Integer> listeCommDel = new ArrayList<>();


    public CommentaireCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_commentaire_listeview, parent, false);

        return configureView(context, convertView, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        configureView(context, view, cursor);
    }

    /**
     * Construit une vue sur base du layout joueur_list_view_item et
     * de l'élément courant du curseur
     * @param context
     * @param convertView
     * @param cursor
     * @return
     */
    public View configureView(Context context, View convertView, Cursor cursor) {
        final Commentaire commentaire = DAO.getCommentaireFromCursor(cursor);

        final CheckedTextView texteC = (CheckedTextView)convertView.findViewById(R.id.item_commentaire_etablissement);
        texteC.setText(commentaire.getTexte());

        texteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (texteC.isChecked()) {
                    texteC.setChecked(false);
                    listeCommDel.remove(new Integer(commentaire.getId()));
                    Log.i("TAILLE arraylist :", "apres un remove :" + listeCommDel.size());

                } else {
                    texteC.setChecked(true);
                    listeCommDel.add(commentaire.getId());
                    Log.i("TAILLE arraylist :", "apres un add :" + listeCommDel.size());
                }
            }
        });
        return convertView;
    }


    public ArrayList<Integer> getListeCommDel() {
        return listeCommDel;
    }

}
