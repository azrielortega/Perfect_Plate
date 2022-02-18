package com.mobdeve.s14.group4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class FavoriteRecipeHolder extends RecyclerView.ViewHolder{
    private Book book;

    public ImageView ivRecipe;
    private TextView tvRecipe;
    public ImageView ivHeart;

    private CardView cvRecipe;

    private boolean bHeart = true;

    public FavoriteRecipeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        ivRecipe = itemView.findViewById(R.id.template_fr_recipe_iv_cover);
        tvRecipe = itemView.findViewById(R.id.template_fr_recipe_tv_name);
        ivHeart = itemView.findViewById(R.id.template_fr_iv_heart);
        cvRecipe = itemView.findViewById(R.id.template_fr_cv_recipe);

        ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bHeart == false) {
                    ivHeart.setImageResource(R.drawable.vectorheart_on);
                    bHeart = true;
                    DataHelper.userDatabase.addFaveRecipe(book);
                }
                else{
                    ivHeart.setImageResource(R.drawable.heart_off);
                    bHeart = false;
                    DataHelper.userDatabase.removeFaveRecipe(book);
                }
            }
        });
    }

    public void setRecipe(Book book){ this.book = book; }

    public void setRecipePic(int image){
        ivRecipe.setImageResource(image);
    }

    public void setRecipeName (String name){
        tvRecipe.setText(name);
    }

    public CardView getCard (){
        return cvRecipe;
    }

    public boolean getLiked(){
        return bHeart;
    }
}


