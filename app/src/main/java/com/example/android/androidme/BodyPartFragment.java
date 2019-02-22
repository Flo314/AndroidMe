package com.example.android.androidme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidme.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment {

    // étiquette pour la journalisation
    private static final String TAG = "BodyPartFragment";

    // stocker une liste d'id d'image et l'index
    private List<Integer>mImageIds;
    private int mListIndex;

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

        //Si l'id d'une image exist ? définir la ressource image sur le bon élément de cette liste
        if(mImageIds != null){
            imageView.setImageResource((mImageIds.get(mListIndex)));
        }else{
            Log.v(TAG, "La liste d'identifiant d'image de ce fragment est nulle");
        }

        // retourne la vue racine
        return rootView;
    }

    // methode qui garde une trace de la liste d'image et quelle image de la liste est actuellement affiché
    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setmListIndex(int index) {
        mListIndex = index;
    }
}
