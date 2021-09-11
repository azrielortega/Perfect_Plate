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

//    public static final String KEY_RECIPE_NAME = "KEY_RECIPE_NAME";
//    public static final String KEY_RECIPE_PIC = "KEY_RECIPE_PIC";
//    public static final String KEY_RECIPE_FAV = "KEY_RECIPE_FAV";
//    public static final String KEY_RECIPE_STARS = "KEY_RECIPE_STARS";
//    public static final String KEY_CONTRIBUTOR_PIC = "KEY_CONTRIBUTOR_PIC";
//    public static final String KEY_CONTRIBUTOR_NAME = "KEY_CONTRIBUTOR_NAME";
//    public static final String KEY_RECIPE_DESCRIPTION = "KEY_RECIPE_DESCRIPTION";
//    public static final String KEY_RECIPE_REVIEWS_COUNT = "KEY_RECIPE_REVIEWS_COUNT";

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";
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

        recentViewHolder.getRecentCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);
                i.putExtra(KEY_RECIPE_ID, foodList.get(recentViewHolder.getBindingAdapterPosition()).getId());


//                i.putExtra(KEY_RECIPE_NAME, foodList.get(recentViewHolder.getBindingAdapterPosition()).getRecipeName());
//                i.putExtra(KEY_RECIPE_PIC, foodList.get(recentViewHolder.getBindingAdapterPosition()).getRecipePic());
//                i.putExtra(KEY_RECIPE_FAV, foodList.get(recentViewHolder.getBindingAdapterPosition()).getFoodFave());
//                i.putExtra(KEY_RECIPE_STARS, foodList.get(recentViewHolder.getBindingAdapterPosition()).getRating());
//                i.putExtra(KEY_CONTRIBUTOR_PIC, foodList.get(recentViewHolder.getBindingAdapterPosition()).getContributorPic());
//                i.putExtra(KEY_CONTRIBUTOR_NAME, foodList.get(recentViewHolder.getBindingAdapterPosition()).getContributorName());
//                i.putExtra(KEY_RECIPE_DESCRIPTION, foodList.get(recentViewHolder.getBindingAdapterPosition()).getDescription());
//                i.putExtra(KEY_RECIPE_REVIEWS_COUNT, foodList.get(recentViewHolder.getBindingAdapterPosition()).getReviewCount());

                v.getContext().startActivity(i);

            }
        });

        return recentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecentViewHolder holder, int position) {
        holder.setIvRecentPic(foodList.get(position).getRecipePic());
        holder.setTvRecentName(foodList.get(position).getRecipeName());
        holder.setTvRecentAuthor(foodList.get(position).getContributorName());
        holder.setTvRecentRatings(foodList.get(position).getRatingString());
        holder.setTvRecentReviews(foodList.get(position).getReviewCount());
        holder.setTvRecentHearts(foodList.get(position).getFaveCount());


    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }
}
