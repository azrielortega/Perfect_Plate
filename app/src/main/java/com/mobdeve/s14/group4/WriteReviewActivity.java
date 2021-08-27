package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WriteReviewActivity extends AppCompatActivity {

    private ImageButton ibSubmit;
    private ImageButton ibBack;

    private Boolean submitted = false;
    public static final String KEY_SUBMITTED = "KEY_SUBMITTED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        this.ibSubmit = findViewById(R.id.ib_review_submit);
        this.ibBack = findViewById(R.id.ib_review_back);

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.ibSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

}