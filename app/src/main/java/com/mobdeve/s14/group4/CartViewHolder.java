package com.mobdeve.s14.group4;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCartPic;
    private TextView tvCartTitle;
    private TextView tvCartCategory;
    private TextView tvCartQuantity;

    public ImageButton ibCartAdd;
    public ImageButton ibCartSub;

    private OrderDetails orderDetails;

    public CartViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.ivCartPic = itemView.findViewById(R.id.iv_cart_pic);
        this.ivCartPic.setClipToOutline(true);
        this.tvCartTitle = itemView.findViewById(R.id.tv_cart_title);
        this.tvCartCategory = itemView.findViewById(R.id.tv_cart_category);
        this.tvCartQuantity = itemView.findViewById(R.id.tv_cart_qty);

        this.ibCartAdd = itemView.findViewById(R.id.ib_cart_add);
        this.ibCartSub = itemView.findViewById(R.id.ib_cart_sub);

//        ibCartAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                orderDetails.addOne();
//                setCartQuantity(orderDetails.getQuantity());
//            }
//        });

//        ibCartSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                orderDetails.subOne();
//                setCartQuantity(orderDetails.getQuantity());
//            }
//        });
    }

    public void setOrderDetails(OrderDetails orderDetails){
        this.orderDetails = orderDetails;

        setCartTitle(orderDetails.getBook().getBookName());
        setCartCategory(orderDetails.getBook().getCategory());
        setCartQuantity(orderDetails.getQuantity());
    }

    public void setCartTitle(String title){
        this.tvCartTitle.setText(title);
    }

    public void setCartCategory(String category){
        this.tvCartCategory.setText(category);
    }

    public void setCartQuantity(int quantity){
        this.tvCartQuantity.setText(String.valueOf(quantity));
    }
}
