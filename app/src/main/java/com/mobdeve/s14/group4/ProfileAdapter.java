package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileRecipeHolder>{

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";

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

        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);
                i.putExtra(KEY_RECIPE_ID, data.get(holder.getBindingAdapterPosition()).getId());
                v.getContext().startActivity(i);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfileRecipeHolder holder, int position) {
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
