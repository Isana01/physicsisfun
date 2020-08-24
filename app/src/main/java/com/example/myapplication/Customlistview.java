package com.example.myapplication;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;

public class Customlistview extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] fruitname;
    private final Integer[] imgid;
    private final String[] desc;
    public Customlistview(Activity context,
                          String[] fruitname, Integer[] imgid,String [] desc) {
        super(context, R.layout.listview_layout, fruitname);
        this.context = context;
        this.fruitname = fruitname;
        this.imgid = imgid;
        this.desc = desc;

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listview_layout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txt1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtTitle.setText(fruitname[position]);
        txtTitle1.setText(desc[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;
    }
}