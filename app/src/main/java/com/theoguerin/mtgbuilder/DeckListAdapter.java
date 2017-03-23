package com.theoguerin.mtgbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by olivier on 27/01/17.
 */

public class DeckListAdapter extends ArrayAdapter<Deck>{
    public DeckListAdapter(Context context, ArrayList<Deck> decks){
        super(context, 0, decks);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Deck deck = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_deck_item, parent, false);
        }
        TextView nom = (TextView) convertView.findViewById(R.id.nom_deck_liste);
        TextView nbCartes = (TextView) convertView.findViewById(R.id.nombre_cartes_deck_liste);

        nom.setText(deck.getNom());
        nbCartes.setText(Integer.toString(deck.getNombreCartes()));

        return convertView;
    }
}
