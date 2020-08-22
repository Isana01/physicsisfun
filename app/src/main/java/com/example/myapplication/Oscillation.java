package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;


import processing.android.PFragment;
import processing.core.PVector;


public class Oscillation extends FragmentActivity {
    BlankFragment blankFragment;
    ScrollingFragment scrollingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oscillation);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        blankFragment = new BlankFragment();
        scrollingFragment = new ScrollingFragment();
        transaction.replace(R.id.descriptionFrame,scrollingFragment);
//        transaction.replace(R.id.simulationFrame,blankFragment);
        FrameLayout simulationFrame = findViewById(R.id.simulationFrame);



        PendulumApplet pendulumApplet = new PendulumApplet();
        PFragment fragment = new PFragment(pendulumApplet);
        fragment.setView(simulationFrame,this);

        transaction.commit();

    }
}