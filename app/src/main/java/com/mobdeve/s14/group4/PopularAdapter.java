package com.mobdeve.s14.group4;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {
    private ArrayList<Popular> popularList;

    public PopularAdapter(ArrayList<Popular> p){
        this.popularList = p;
    }

    @NonNull
    @NotNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View iv = inflater.inflate(R.layout.popular_template, parent, false);

        PopularViewHolder popularViewHolder = new PopularViewHolder(iv);
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularViewHolder holder, int position) {
        holder.setIvPopularPic(popularList.get(position).getPopularPic());
        holder.setTvPopularName(popularList.get(position).getPopularName());
    }

    @Override
    public int getItemCount() {
        return this.popularList.size();
    }
}
