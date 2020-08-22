package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChapterRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<ChapterItemViewModel> model;
    public ChapterRecyclerAdapter(ArrayList<ChapterItemViewModel> model) {
        this.model = model;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ChapterItemModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ChapterItemModel) holder).bindData(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.chapter_item_view;
    }
}

class ChapterItemModel extends RecyclerView.ViewHolder {
    private TextView chapName, chapDesc;

    public ChapterItemModel(@NonNull View itemView) {
        super(itemView);
        chapName = itemView.findViewById(R.id.chapnum);
        chapDesc = itemView.findViewById(R.id.chapname);
    }

    public void bindData(ChapterItemViewModel model) {
        chapName.setText(model.getChapName());
        chapDesc.setText(model.getChapDesc());
    }

}