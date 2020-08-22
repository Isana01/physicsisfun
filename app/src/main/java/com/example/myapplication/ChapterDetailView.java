package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ChapterDetailView extends AppCompatActivity {
    ImageView chapterImage;
    TextView chapterName,chapterDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail_view);
        HashMap<String,String[]> map = new HashMap<>();
        String[] force = {"force1","force2","force3"};
        String[] oscillation = {"Oscillating mass","Simple Harmonic Motion","Projectile Motion"};
        String[] vectors = {"Vector1","Vector2","Resultant vector"};
        String[] quantum = {"Properties of an Atom"};
        String[] space = {"Coronal Mass Ejection"};
        map.put("forces",force);
        map.put("oscillations",oscillation);
        map.put("vectors",vectors);
        map.put("quantum physics",quantum);
        map.put("space",space);


        chapterName = findViewById(R.id.chapterDetailName);
        chapterDesc = findViewById(R.id.chapterDetailDesc);
        chapterImage = findViewById(R.id.chapterImage);
        chapterImage.setImageResource(getIntent().getIntExtra("image",R.drawable.galaxy));
//        RecyclerView chaptersView = findViewById(R.id.chapterRecyclerView);
        //ArrayList<String> viewModel = new ArrayList<>();
        chapterName.setText(getIntent().getStringExtra("chapter"));
        chapterDesc.setText(getIntent().getStringExtra("desc"));
        ListView topicsListView = findViewById(R.id.topicsListView);

        //for (int i =0 ; i < 2 ; i++) viewModel.add(chapterDesc.getText() + " " + i);
        TopicsAdapter adapter = new TopicsAdapter(map.get(chapterDesc.getText().toString().toLowerCase()));
        topicsListView.setAdapter(adapter);
        /*
        ChapterRecyclerAdapter recyclerAdapter = new ChapterRecyclerAdapter(viewModel);
        chaptersView.setLayoutManager(new LinearLayoutManager(this));
        chaptersView.setHasFixedSize(true);
        chaptersView.setAdapter(recyclerAdapter);

         */

    }
}