package com.example.android.androidme;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    // contructeur vide pour instancier le fragment
    public MasterListFragment(){}

    /**
     * Gonfle le fragment et définit n'importe quelle ressource d'image
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // gonfler le fragment
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // obtenir une référence à la vue de l'image
       GridView gridView = (GridView) rootView.findViewById(R.id.image_grid_view);

        //créer un adapteur
        // afficher toutes les images
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //mettre l'adapteur dans le gridView
        gridView.setAdapter(mAdapter);

        // retourne la vue racine
        return rootView;
    }
}
