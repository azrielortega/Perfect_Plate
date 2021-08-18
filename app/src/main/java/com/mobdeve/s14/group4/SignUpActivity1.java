package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUpActivity1 extends AppCompatActivity {

    private Button btnNext;

    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        btnNext = findViewById(R.id.signup1_btn_next);
        tvSignIn = findViewById(R.id.signup1_tv_signin);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity1.this, SignUpActivity2.class);
                SignUpActivity1.this.startActivity(intent);
            }
        });
    }
}