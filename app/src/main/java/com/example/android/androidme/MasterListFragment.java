package com.example.android.androidme;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    // définir une interface qui déclenche un rappel dans l'activité hôte
    OnImageClickListener mCallbach;

    // l'interface appelle la méthode onImageSelected dans l'activité hôte
    public interface OnImageClickListener {
        // méthode de callback
        void onImageSelected(int position);// position du clic sur la grille
    }

    // cela permet de s'assurer que l'activité a été implémentée par le rappel
    // c'est là que le fragment s'attache à son activté d'accueil
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallbach = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnImageClickListener");
        }
    }

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

        // déclencher le rappel sur un clic
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallbach.onImageSelected(position);
            }
        });

        // retourne la vue racine
        return rootView;
    }
}
