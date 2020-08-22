package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.BlankFragment;
import com.example.myapplication.BlankFragmentprojectile;
import com.example.myapplication.PendulumApplet;
import com.example.myapplication.R;
import com.example.myapplication.ScrollingFragment;
import com.example.myapplication.ScrollingFragmentprojectile;

import processing.android.PFragment;

public class Projectile extends FragmentActivity {

    BlankFragmentprojectile blankFragment;
    ScrollingFragmentprojectile scrollingFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectile);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        blankFragment = new BlankFragment();
        scrollingFragment = new ScrollingFragmentprojectile();
        transaction.replace(R.id.descriptionprojectile,scrollingFragment);
//        transaction.replace(R.id.simulationFrame,blankFragment);
        FrameLayout simulationprojectile = findViewById(R.id.simulationprojectile);



        BlankFragmentprojectile projectile = new BlankFragmentprojectile();
        PFragment fragment = new PFragment(projectile);
        fragment.setView(simulationprojectile,this);

        transaction.commit();


    }
}