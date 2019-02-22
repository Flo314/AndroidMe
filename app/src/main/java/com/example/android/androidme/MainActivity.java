package com.example.android.androidme;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // créer une instance de BodyPartFragment et l'affocher en utilisant le FragmentManager
        BodyPartFragment headFragment = new BodyPartFragment();

        // utilise le FragmentManager pour ajouter le fragment à l'écran
        // getSupportManager donne un gestionnaire de fragment qui utilise les API de la bibliothèque de support
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Fragment transaction : ajoute le fragment dans le container
        // beginTransaction démarre une nouvelle transaction
        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit();
    }
}
