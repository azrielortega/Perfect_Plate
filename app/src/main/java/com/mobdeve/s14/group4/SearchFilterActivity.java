package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SearchFilterActivity extends AppCompatActivity {
    private TextView tvCategory;
    private ImageButton ibBack;
    private CardView cvFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        this.tvCategory = findViewById(R.id.tv_category);
        this.ibBack = findViewById(R.id.ib_category_back);
        this.cvFood = findViewById(R.id.cv_filter_result);

        Intent i = getIntent();

        String categ = i.getStringExtra(SearchActivity.KEY_CATEGORY);
        this.tvCategory.setText(categ);

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}