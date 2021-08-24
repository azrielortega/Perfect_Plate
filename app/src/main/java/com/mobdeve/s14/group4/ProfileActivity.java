package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvRecipes;
    private RecyclerView.LayoutManager manager;
    private ProfileAdapter adapter;

    private ArrayList<Recipe> data;

    private ImageView ivEdit;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        this.initRecyclerView();
        this.initComponents();
        Log.d("SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
    }

    private void initComponents(){
        ivEdit = findViewById(R.id.profile_iv_edit);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("PROFILE SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {

        rvRecipes = findViewById(R.id.profile_rv_recipes);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRecipes.setLayoutManager(manager);

        this.initData();
        adapter = new ProfileAdapter(data);
        rvRecipes.setAdapter(adapter);

    }

    private void initData() {
        data = new ArrayList<Recipe>();

        data.add(new Recipe (R.drawable.takoyaki, "Takoyaki", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.adobo, "Adobo", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.curry, "Curry", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.ramen, "Ramen", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));

    }
}