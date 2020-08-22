package com.example.myapplication;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.ui.main.Projectile;

import java.util.ArrayList;

public class TopicsAdapter extends BaseAdapter {
    String[] model;
    public TopicsAdapter(String[] model) {
        this.model = model;
    } //constructor
    @Override
    public int getCount() {
        return model.length;
    } //array count

    @Override
    public Object getItem(int i) {
        return model[i];
    } //get array items

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
              view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_list_item,viewGroup,false);
        }
        final int position = i;
        final ViewGroup finalViewGroup =viewGroup;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((String)getItem(position)).equals("Oscillating mass")) {
                    Intent i = new Intent(finalViewGroup.getContext(),Oscillation.class);
                    finalViewGroup.getContext().startActivity(i);
                }
                else if(((String)getItem(position)).equals("Projectile Motion")){
                    Intent i = new Intent(finalViewGroup.getContext(), Projectile.class);
                    finalViewGroup.getContext().startActivity(i);

                }
                else if(((String)getItem(position)).equals("Simple Harmonic Motion")){
                    Intent i = new Intent(finalViewGroup.getContext(), Harmonic.class);
                    finalViewGroup.getContext().startActivity(i);

                }

            }
        });
        view.setClickable(true);
        TextView topicName = view.findViewById(R.id.topicNameItem);
        topicName.setText((String)getItem(i));

        return view;
    }
}
