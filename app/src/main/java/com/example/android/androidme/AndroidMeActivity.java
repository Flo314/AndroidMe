package com.example.android.androidme;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        // créer un nouveau fragment que si il n'y en a aucun sauvegardés
        if (savedInstanceState == null){

            // créer une instance de BodyPartFragment et l'afficher en utilisant le FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();

            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(1);

            // utilise le FragmentManager pour ajouter le fragment à l'écran
            // getSupportManager donne un gestionnaire de fragment qui utilise les API de la bibliothèque de support
            FragmentManager fragmentManager = getSupportFragmentManager();
            // Fragment transaction : ajoute le fragment dans le container
            // beginTransaction démarre une nouvelle transaction
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();

        }
    }
}
