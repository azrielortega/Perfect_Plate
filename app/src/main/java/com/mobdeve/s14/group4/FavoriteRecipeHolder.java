package com.mobdeve.s14.group4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class FavoriteRecipeHolder extends RecyclerView.ViewHolder{

    private ImageView ivRecipe;
    private TextView tvRecipe;

    public FavoriteRecipeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        ivRecipe = itemView.findViewById(R.id.template_fr_recipe_iv_cover);
        tvRecipe = itemView.findViewById(R.id.template_fr_recipe_tv_name);
    }

    public void setRecipePic(int image){
        ivRecipe.setImageResource(image);
    }

    public void setRecipeName (String name){
        tvRecipe.setText(name);
    }
}
