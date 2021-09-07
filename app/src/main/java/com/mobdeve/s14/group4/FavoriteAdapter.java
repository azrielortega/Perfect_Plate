package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteRecipeHolder> {

    public static final String KEY_RECIPE_NAME = "KEY_RECIPE_NAME";
    public static final String KEY_RECIPE_PIC = "KEY_RECIPE_PIC";

    private ArrayList<Recipe> data;

    private CardView cvRecipe;

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

        holder.getCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);
                i.putExtra(KEY_RECIPE_NAME, data.get(holder.getBindingAdapterPosition()).getRecipeName());
                i.putExtra(KEY_RECIPE_PIC, data.get(holder.getBindingAdapterPosition()).getRecipePic());
                v.getContext().startActivity(i);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavoriteRecipeHolder holder, int position) {
        holder.setRecipePic(data.get(position).getRecipePic());
        holder.setRecipeName(data.get(position).getRecipeName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
