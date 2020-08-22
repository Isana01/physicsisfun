package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import processing.android.PFragment;

public class Harmonic extends FragmentActivity {
    BlankFragmentHarmonic blankFragment;
    ScrollingFragmentHarmonic scrollingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmonic);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        blankFragment = new BlankFragment();
        scrollingFragment = new ScrollingFragmentHarmonic();
        transaction.replace(R.id.descriptionharmonic,scrollingFragment);
//        transaction.replace(R.id.simulationFrame,blankFragment);
        FrameLayout simulationharmonic = findViewById(R.id.simulationharmonic);
        FrameLayout de = findViewById(R.id.de);



        BlankFragmentHarmonic harmonic = new BlankFragmentHarmonic();
        PFragment fragment = new PFragment(harmonic);
        fragment.setView(simulationharmonic,this);

        transaction.commit();
    }
}