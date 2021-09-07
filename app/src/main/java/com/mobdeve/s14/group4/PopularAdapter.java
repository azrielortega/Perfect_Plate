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
    public static final String KEY_RECIPE_FAV = "KEY_RECIPE_FAV";
    public static final String KEY_RECIPE_STARS = "KEY_RECIPE_STARS";
    public static final String KEY_CONTRIBUTOR_PIC = "KEY_CONTRIBUTOR_PIC";
    public static final String KEY_CONTRIBUTOR_NAME = "KEY_CONTRIBUTOR_NAME";
    public static final String KEY_RECIPE_DESCRIPTION = "KEY_RECIPE_DESCRIPTION";
    public static final String KEY_RECIPE_REVIEWS_COUNT = "KEY_RECIPE_REVIEWS_COUNT";


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
                i.putExtra(KEY_RECIPE_NAME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRecipeName());
                i.putExtra(KEY_RECIPE_PIC, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRecipePic());
//                i.putExtra(KEY_RECIPE_FAV, foodList.get(popularViewHolder.getBindingAdapterPosition()).getFoodFave());
//                i.putExtra(KEY_RECIPE_STARS, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRating());
                i.putExtra(KEY_CONTRIBUTOR_PIC, foodList.get(popularViewHolder.getBindingAdapterPosition()).getContributorPic());
                i.putExtra(KEY_CONTRIBUTOR_NAME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getContributorName());
                i.putExtra(KEY_RECIPE_DESCRIPTION, foodList.get(popularViewHolder.getBindingAdapterPosition()).getDescription());
//                i.putExtra(KEY_RECIPE_REVIEWS_COUNT, foodList.get(popularViewHolder.getBindingAdapterPosition()).getReviewCount());

                v.getContext().startActivity(i);

            }
        });
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularViewHolder holder, int position) {
        holder.setIvPopularPic(foodList.get(position).getRecipePic());
        holder.setTvPopularName(foodList.get(position).getRecipeName());
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }
}
