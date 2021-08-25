package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentViewHolder>{
    private ArrayList<Recipe> foodList;

    public RecentAdapter(ArrayList<Recipe> p){
        this.foodList = p;
    }

    @NonNull
    @NotNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recent_template, parent, false);

        RecentViewHolder recentViewHolder = new RecentViewHolder(itemView);

        return recentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecentViewHolder holder, int position) {
        holder.setIvRecentPic(foodList.get(position).getFoodPic());
        holder.setTvRecentName(foodList.get(position).getFoodName());
        holder.setTvRecentAuthor(foodList.get(position).getContributorName());
        holder.setTvRecentRatings(foodList.get(position).getRating());
        holder.setTvRecentReviews(foodList.get(position).getReviewCount());
        holder.setTvRecentHearts(foodList.get(position).getFoodFave());
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }
}
