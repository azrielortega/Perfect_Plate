package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class AddAdminActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        initComponents();

//        new UserDatabase().getAllUsers(new CallbackListener() {
//            @Override
//            public void onSuccess(Object o) {
//                ArrayList<User> users = (ArrayList<User>) o;
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//        });
    }

    private void initComponents() {


    }
}