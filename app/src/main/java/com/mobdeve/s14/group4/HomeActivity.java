package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvPopular;
    private ArrayList<Recipe> foodList;

    private RecyclerView.LayoutManager popularManager;
    private PopularAdapter popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.initRecyclerView();
    }

    private void initRecyclerView(){

        DataHelper helper = new DataHelper();
        this.foodList = helper.initFood();
        this.rvPopular = findViewById(R.id.rv_home_main);

        this.popularManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        this.rvPopular.setLayoutManager(this.popularManager);

        //this.initData();
        this.popularAdapter = new PopularAdapter(this.foodList);
        this.rvPopular.setAdapter(this.popularAdapter);
    }

//    private void initData(){
//        this.foodList = new ArrayList<Food>();
//
//        this.foodList.add(new Food(R.drawable.popular1, "Food 1"));
//        this.foodList.add(new Food(R.drawable.popular2, "Meow Meow"));
//        this.foodList.add(new Food(R.drawable.popular3, "Poopers poopie meow meow"));
//    }
}