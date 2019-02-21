package com.example.android.androidme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidme.data.AndroidImageAssets;

public class BodyPartFragment extends Fragment {

    // contructeur vide pour instancier le fragment
    public BodyPartFragment(){}

    /**
     * Gonfle le fragment et définit n'importe quelle ressource d'image
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // gonfler le fragment
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // obtenir une référence à la vue de l'image
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        //définir l'image
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        // retourne la vue racine
        return rootView;
    }
}
