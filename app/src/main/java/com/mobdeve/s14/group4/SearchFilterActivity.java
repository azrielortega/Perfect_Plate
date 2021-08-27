package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchFilterActivity extends AppCompatActivity {
    private TextView tvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        this.tvCategory = findViewById(R.id.tv_category);

        Intent i = getIntent();

        String categ = i.getStringExtra(SearchActivity.KEY_CATEGORY);
        this.tvCategory.setText(categ);


    }
}