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

public class ProfileAdapter extends RecyclerView.Adapter<ProfileBookHolder>{

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";

    private ArrayList<Book> data;

    public ProfileAdapter(ArrayList<Book> tempData){
        data = tempData;
    }


    @NonNull
    @NotNull
    @Override
    public ProfileBookHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_recipe_template, parent, false);

        ProfileBookHolder holder = new ProfileBookHolder(view);

        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RecipeDetailsActivity.class);
                i.putExtra(KEY_RECIPE_ID, data.get(holder.getBindingAdapterPosition()).getId());
                v.getContext().startActivity(i);
            }
        });

        holder.ivBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataHelper.userDatabase.removeUserRecipe(data.get(holder.getBindingAdapterPosition()).getId());
//                data = DataHelper.user.getUserRecipes();
//                notifyDataSetChanged();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProfileBookHolder holder, int position) {
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
