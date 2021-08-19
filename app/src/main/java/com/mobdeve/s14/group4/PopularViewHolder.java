package com.mobdeve.s14.group4;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class PopularViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivPopularPic;
    private TextView tvPopularName;

    public PopularViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.ivPopularPic = itemView.findViewById(R.id.iv_popular_pic);
        //this.tvPopularName = itemView.findViewById(R.id.tv_popular_name);
    }

    public void setIvPopularPic(int p){
        this.ivPopularPic.setImageResource(p);
    }

    public void setTvPopularName(String n){
        this.tvPopularName.setText(n);
    }
}
