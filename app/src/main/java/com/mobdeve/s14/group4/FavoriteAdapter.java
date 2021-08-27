package com.mobdeve.s14.group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteRecipeHolder> {

    private ArrayList<Recipe> data;

    public FavoriteAdapter(ArrayList<Recipe> tempData){
        data = tempData;
    }


    @NonNull
    @NotNull
    @Override
    public FavoriteRecipeHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.template_favoriterecipe, parent, false);

        FavoriteRecipeHolder holder = new FavoriteRecipeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavoriteRecipeHolder holder, int position) {
        holder.setRecipePic(data.get(position).getFoodPic());
        holder.setRecipeName(data.get(position).getFoodName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
