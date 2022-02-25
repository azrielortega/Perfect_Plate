package com.mobdeve.s14.group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private Order cart;

    public CartAdapter(Order cart){
        this.cart = cart;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cart_item_template, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(itemView);

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position) {
        holder.setOrderDetails(cart.getOrderDetails().get(position));
        Picasso.with(holder.itemView.getContext())
                .load(cart.getOrderDetails().get(position).getBook().getUploadImage().getmImageUrl())
                .placeholder(R.drawable.perfect_plate_transparent_bg)
                .fit()
                .centerCrop()
                .into(holder.ivCartPic);
    }

    @Override
    public int getItemCount() {
        return this.cart.getOrderDetails().size();
    }
}
