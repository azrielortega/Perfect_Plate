package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    public TextView tvCustomer;
    public TextView tvAddressStreet;
    public TextView tvAddressArea;
    public TextView tvTotal;

    public RecyclerView rvOrder;
    public RecyclerView.LayoutManager rvOrderManager;
    public CheckoutAdapter checkoutAdapter;

    public Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        this.tvCustomer = findViewById(R.id.tv_checkout_name);
        this.tvAddressStreet = findViewById(R.id.tv_checkout_street_address);
        this.tvAddressArea = findViewById(R.id.tv_city_province_postal);
        this.tvTotal = findViewById(R.id.tv_checkout_total);

        this.btnPlaceOrder = findViewById(R.id.btn_checkout_place_order);

        this.rvOrder = findViewById(R.id.rv_checkout_summary);
        this.rvOrderManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.checkoutAdapter = new CheckoutAdapter(DataHelper.user.getCart().getOrderDetails());

        this.rvOrder.setLayoutManager(this.rvOrderManager);
        this.rvOrder.setAdapter(this.checkoutAdapter);

        setDetails();
    }

    private void setDetails(){
        this.tvCustomer.setText(DataHelper.user.getFullName());

        Address address = DataHelper.user.getAddress();
        this.tvAddressStreet.setText(address.getStreet());
        this.tvAddressArea.setText(address.getCity() + ", " + address.getState() + " " + address.getPostalCode());

        this.tvTotal.setText(String.format("%.2f", DataHelper.user.getCart().getTotal()));
    }
}