package com.example.android.androidme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.androidme.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
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
    }
}
