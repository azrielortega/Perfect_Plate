package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {
    private ArrayList<Recipe> foodList;

    public static final String KEY_RECIPE_NAME = "KEY_RECIPE_NAME";
    public static final String KEY_RECIPE_PIC = "KEY_RECIPE_PIC";


    public PopularAdapter(ArrayList<Recipe> p){
        this.foodList = p;
    }

    @NonNull
    @NotNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.popular_template, parent, false);

        PopularViewHolder popularViewHolder = new PopularViewHolder(itemView);

        popularViewHolder.getCvPopularCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);
                i.putExtra(KEY_RECIPE_NAME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getFoodName());
                i.putExtra(KEY_RECIPE_PIC, foodList.get(popularViewHolder.getBindingAdapterPosition()).getFoodPic());

                v.getContext().startActivity(i);

            }
        });
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularViewHolder holder, int position) {
        holder.setIvPopularPic(foodList.get(position).getFoodPic());
        holder.setTvPopularName(foodList.get(position).getFoodName());
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }
}
