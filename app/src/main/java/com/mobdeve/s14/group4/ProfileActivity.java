package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvRecipes;
    private RecyclerView.LayoutManager manager;
    private ProfileAdapter adapter;

    private ArrayList<Recipe> data;

    private ImageView ivEdit;
    private TextView tvName;
    private TextView tvUsername;
    private FirebaseUser user;
    private DatabaseReference databaseReference;

    private String fname;
    private String lname;
    private String username;

    private ImageButton ibBack;
    private ImageButton ibAdd;
    private Button btnLogout;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.initRecyclerView();
        this.initComponents();
//        this.initGoogleComponents();
    }

    private void signOut(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleSignInClient.signOut();
        FirebaseAuth.getInstance().signOut();
    }

    private void initComponents(){
        ivEdit = findViewById(R.id.profile_iv_edit);
        this.tvName = findViewById(R.id.profile_tv_name);
        this.tvUsername = findViewById(R.id.profile_tv_username);
        ibBack = findViewById(R.id.ib_profile_back);
        ibAdd = findViewById(R.id.ib_profile_add);
        this.btnLogout = findViewById(R.id.btn_profile_logout);

        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, CreateRecipeActivity1.class);
                startActivity(i);
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //authStateListener.onAuthStateChanged(FirebaseAuth.getInstance());
        user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("PROFILE SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fname = dataSnapshot.child("fname").getValue(String.class);
                lname = dataSnapshot.child("lname").getValue(String.class);
                username = dataSnapshot.child("username").getValue(String.class);

                String fullName = fname + " " + lname;
                tvName.setText(fullName);
                tvUsername.setText(username);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FAIL TAG", "Failed to read value.", error.toException());
            }
        });

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                ProfileActivity.this.startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
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