package com.example.mdoming15.campusalma;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdoming15.campusalma.Model.DAO;
import com.example.mdoming15.campusalma.Model.Etablissement;
import com.example.mdoming15.campusalma.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Wiiki on 22-11-15.
 */
class EtablissementCursorAdapter extends CursorAdapter {

    public EtablissementCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_etablissement_listview, parent, false);

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
        Etablissement etablissement = DAO.getEtablissementFromCursor(cursor);

        TextView nomE = (TextView)convertView.findViewById(R.id.item_nom_etablissement);
        TextView noteE = (TextView)convertView.findViewById(R.id.item_note_etablissement);

        //ImageView imageNote = (ImageView)convertView.findViewById(R.id.image_item);

        nomE.setText(etablissement.getNom());
        //nomE.setTextColor(Color.WHITE);et
        noteE.setText(Integer.toString(etablissement.getNote()));
        //noteE.setTextColor(Color.WHITE);

        /* Prenons les images dans les assets
        if (etablissement.getImage() != null){
            AssetManager assetManager = context.getAssets();

            try{
                InputStream is = context.getAssets().open(etablissement.getImage());
                Drawable image = Drawable.createFromStream(is, null); // createFromPath existe aussi
                imageNote.setImageDrawable(image);
            } catch (IOException e) {
                // Ne rien faire, à la limitte, un log
                e.printStackTrace();
            }
        }
        else{
            imageNote.setImageDrawable(null);
        }

         */

        return convertView;
    }


}
