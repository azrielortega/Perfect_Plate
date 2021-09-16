package com.mobdeve.s14.group4;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ProfileRecipeHolder extends RecyclerView.ViewHolder {

    public ImageView ivCover;
    public ImageView ivBtnDelete;

    public ProfileRecipeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        ivCover = itemView.findViewById(R.id.pt_recipe_iv_cover);
        ivBtnDelete = itemView.findViewById(R.id.iv_delete_user_recipe);
    }
}
