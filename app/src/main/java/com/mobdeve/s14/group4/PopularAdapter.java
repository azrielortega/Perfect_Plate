package com.mobdeve.s14.group4;

import android.content.ContentValues;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {
    private ArrayList<Recipe> foodList;
    //String id, int recipePic, String recipeName, int foodFave, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
    //String category, String difficulty
    public static final String KEY_RECIPE_CONTRIBUTOR_ID = "KEY_RECIPE_CONTRIBUTOR_ID";
    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";
    public static final String KEY_RECIPE_NAME = "KEY_RECIPE_NAME";
    public static final String KEY_RECIPE_PIC = "KEY_RECIPE_PIC";
    public static final String KEY_RECIPE_FAV = "KEY_RECIPE_FAV";
    public static final String KEY_RECIPE_STARS = "KEY_RECIPE_STARS";
    public static final String KEY_CONTRIBUTOR_PIC = "KEY_CONTRIBUTOR_PIC";
    public static final String KEY_CONTRIBUTOR_NAME = "KEY_CONTRIBUTOR_NAME";
    public static final String KEY_RECIPE_DESCRIPTION = "KEY_RECIPE_DESCRIPTION";
    public static final String KEY_RECIPE_REVIEWS_COUNT = "KEY_RECIPE_REVIEWS_COUNT";
    public static final String KEY_COOKING_TIME = "KEY_COOKING_TIME";
    public static final String KEY_PREP_TIME = "KEY_PREP_TIME";
    public static final String KEY_RECIPE_SERVINGS = "KEY_RECIPE_SERVINGS";
    public static final String KEY_RECIPE_CATEGORY = "KEY_RECIPE_CATEGORY";
    public static final String KEY_RECIPE_DIFFICULTY = "KEY_RECIPE_DIFFICULTY";



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
                i.putExtra(KEY_RECIPE_ID, foodList.get(popularViewHolder.getBindingAdapterPosition()).getId());
//                i.putExtra(KEY_RECIPE_NAME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRecipeName());
//                i.putExtra(KEY_RECIPE_PIC, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRecipePic());
//                i.putExtra(KEY_CONTRIBUTOR_PIC, foodList.get(popularViewHolder.getBindingAdapterPosition()).getContributorPic());
//                i.putExtra(KEY_CONTRIBUTOR_NAME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getContributorName());
//                i.putExtra(KEY_RECIPE_DESCRIPTION, foodList.get(popularViewHolder.getBindingAdapterPosition()).getDescription());
//                i.putExtra(KEY_RECIPE_REVIEWS_COUNT, foodList.get(popularViewHolder.getBindingAdapterPosition()).getReviewCount());
//
//                i.putExtra(KEY_RECIPE_FAV, foodList.get(popularViewHolder.getBindingAdapterPosition()).getFaveCount());
//                i.putExtra(KEY_RECIPE_STARS, foodList.get(popularViewHolder.getBindingAdapterPosition()).getRating());
//                i.putExtra(KEY_RECIPE_CONTRIBUTOR_ID, foodList.get(popularViewHolder.getBindingAdapterPosition()).getContributorId());
//                i.putExtra(KEY_COOKING_TIME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getCookingTime());
//                i.putExtra(KEY_PREP_TIME, foodList.get(popularViewHolder.getBindingAdapterPosition()).getPrepTime());
//                i.putExtra(KEY_RECIPE_SERVINGS, foodList.get(popularViewHolder.getBindingAdapterPosition()).getServings());
//                i.putExtra(KEY_RECIPE_CATEGORY, foodList.get(popularViewHolder.getBindingAdapterPosition()).getCategory());
//                i.putExtra(KEY_RECIPE_DIFFICULTY, foodList.get(popularViewHolder.getBindingAdapterPosition()).getDifficulty());

                v.getContext().startActivity(i);

            }
        });
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularViewHolder holder, int position) {
        //holder.setIvPopularPic(foodList.get(position).getRecipePic());
        holder.setTvPopularName(foodList.get(position).getRecipeName());
        System.out.println("TESTPIC" + foodList.get(position).getUploadImage().getmImageUrl());
        Picasso.with(holder.itemView.getContext())
                .load(foodList.get(position).getUploadImage().getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.ivPopularPic);
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }
}
