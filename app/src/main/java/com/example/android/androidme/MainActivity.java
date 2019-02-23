package com.example.android.androidme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.androidme.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    // stocker l'emplacement des images cliquées
    // 12 images pour chaque partie du corps et 35 position en tout
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // établir un lien avec le gragment cliqué
    @Override
    public void onImageSelected(int position) {
        // créer un toast qui affiche la position de ce qui a été cliqué
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // divise la position du clic par 12 on identifie le type d'image
        // fragment de tête à 0 fragment de corps 1 fragment de jambe 2
        int bodyPartNumber = position /12;

        // lisIndex sera toujours compris entre 0 et 11
        int listIndex = position - 12*bodyPartNumber;

        // met à jour les variables d'index dans l'image sélectionné
        switch (bodyPartNumber) {
            case 0: headIndex = listIndex;
            break;
            case  1: bodyIndex = listIndex;
            break;
            case  2: legIndex = listIndex;
            break;
            default:break;
        }

        // passer l'infos à l'activité hôte
        // la grouper (paquet -> bundle)
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        // joindre à une nouvelle intention qui lance une nouvelle activité
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // ajout d'un bouton suivant qui déplace de la liste principale à l'autre activité
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
