package com.example.android.androidme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.androidme.R;
import com.example.android.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    // stocker l'emplacement des images cliquées
    // 12 images pour chaque partie du corps et 35 position en tout
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    // distiction des écrans soit simple soit tablette
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // détermine si il crée écran tel ou tablette
        if(findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;

            // supprimer le bouton next
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            // définir le nombre de colonne de vue de la grille
            GridView gridView = (GridView) findViewById(R.id.image_grid_view);
            gridView.setNumColumns(2);

            // creation de nouveau fragment
            if(savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                // head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());

                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());

                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
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

        if(mTwoPane) {

            BodyPartFragment newFragment = new BodyPartFragment();
           // définir l'élément actuellement affiché pour le bon fragment de partie du corps
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    // remplacer l'ancien fragment par le nouveau au clic d'une image
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    // remplacer l'ancien fragment par le nouveau au clic d'une image
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    // remplacer l'ancien fragment par le nouveau au clic d'une image
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:break;
            }
        } else {

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
}
