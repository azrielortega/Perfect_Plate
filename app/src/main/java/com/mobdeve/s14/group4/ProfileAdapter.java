package com.mobdeve.s14.group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileRecipeHolder>{

    private ArrayList<Recipe> data;

    public ProfileAdapter(ArrayList<Recipe> tempData){
        data = tempData;
    }


    @NonNull
    @NotNull
    @Override
    public ProfileRecipeHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_recipe_template, parent, false);

        ProfileRecipeHolder holder = new ProfileRecipeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfileRecipeHolder holder, int position) {
        holder.setCover(data.get(position).getRecipePic());
        Picasso.with(holder.itemView.getContext())
                .load(data.get(position).getUploadImage().getmImageUrl())
                .placeholder(R.drawable.perfect_plate_transparent_bg)
                .fit()
                .centerCrop()
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
