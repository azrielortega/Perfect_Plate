package com.mobdeve.s14.group4;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class FavoriteRecipeHolder extends RecyclerView.ViewHolder{

    private ImageView ivRecipe;
    private TextView tvRecipe;
    private ImageView ivHeart;

    private CardView cvRecipe;

    private boolean bHeart = false;

    public FavoriteRecipeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        ivRecipe = itemView.findViewById(R.id.template_fr_recipe_iv_cover);
        tvRecipe = itemView.findViewById(R.id.template_fr_recipe_tv_name);
        ivHeart = itemView.findViewById(R.id.template_fr_iv_heart);
        cvRecipe = itemView.findViewById(R.id.template_fr_cv_recipe);

        cvRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);

                v.getContext().startActivity(i);
            }
        });

        ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bHeart == false) {
                    ivHeart.setImageResource(R.drawable.vectorheart_on);
                    bHeart = true;
                }
                else{
                    ivHeart.setImageResource(R.drawable.heart_off);
                    bHeart = false;
                }
            }
        });
    }

    public void setRecipePic(int image){
        ivRecipe.setImageResource(image);
    }

    public void setRecipeName (String name){
        tvRecipe.setText(name);
    }

    public CardView getCard (){
        return cvRecipe;
    }
}


