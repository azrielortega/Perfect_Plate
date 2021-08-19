package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvPopular;
    private ArrayList<Popular> popularList;

    private RecyclerView.LayoutManager popularManager;
    private PopularAdapter popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.initRecyclerView();
    }

    private void initRecyclerView(){
//        DataHelper helper = new DataHelper();
//        this.popularList = helper.initPopular();
        this.rvPopular = findViewById(R.id.rv_popular);

        this.initData();
        this.popularManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        this.rvPopular.setLayoutManager(this.popularManager);

        this.popularAdapter = new PopularAdapter(this.popularList);
        this.rvPopular.setAdapter(this.popularAdapter);
    }

    private void initData(){
        this.popularList = new ArrayList<Popular>();

        this.popularList.add(new Popular(R.drawable.person));
        this.popularList.add(new Popular(R.drawable.perfectplate_email));
        this.popularList.add(new Popular(R.drawable.perfectplate_birthday));


    }
}