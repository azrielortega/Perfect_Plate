package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateRecipeActivity1 extends AppCompatActivity {

    private Button btnCreate1;

    private ImageButton ibBack;

    public static final String KEY_RECIPENAME = "KEY_CR_RECIPENAME";
    public static final String KEY_DESCRIPTION = "KEY_CR_DESCRIPTION";
    public static final String KEY_SERVINGS = "KEY_CR_SERVINGS";
    public static final String KEY_COOKINGTIME = "KEY_CR_COOKINGTIME";
    public static final String KEY_PREPTIME= "KEY_CR_PREPTIME";
    public static final String KEY_DIFFICULTY = "KEY_CR_DIFFICULTY";
    public static final String KEY_CATEGORY = "KEY_CR_CATEGORY";
    public static final String KEY_IMAGEURI = "KEY_CR_IMAGEURI";
    public static final String filename = "bitmap.png";


    private EditText etRecipeName;
    private EditText etPrepTime;
    private EditText etServings;
    private EditText etCookingTime;
    private EditText etDescription;

    private Spinner spCategory;

    private TextView tvEasy;
    private TextView tvMedium;
    private TextView tvHard;

    private static String difficulty;

    private LinearLayout llAddPic;

    private ImageView ivRecipePic;

    private Uri dataPic;
    private static Bitmap image;

    private ProgressBar pb;

    public static final int IMAGE_GALLERY_REQUEST = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe1);

        difficulty = "Easy";
        image = null;


        initSpinner();
        initComponents();
    }

    private void initSpinner(){
        spCategory = findViewById(R.id.sp_create1_category);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(CreateRecipeActivity1.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.category));
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(spAdapter);

    }

    private void initComponents() {

        pb = findViewById(R.id.pb_createrecipe1);

        etRecipeName = findViewById(R.id.et_create1_recipe_name);

        etCookingTime = findViewById(R.id.et_create1_cookingtime);
        etPrepTime = findViewById(R.id.et_create1_preptime);
        etServings = findViewById(R.id.et_create1_servings);
        etDescription = findViewById(R.id.et_create1_description);

        btnCreate1 = findViewById(R.id.btn_create1_next);
        ibBack = findViewById(R.id.ib_create1_back);

        tvEasy = findViewById(R.id.tv_create1_easy);
        tvMedium = findViewById(R.id.tv_create1_medium);
        tvHard = findViewById(R.id.tv_create1_hard);

        llAddPic = findViewById(R.id.create1_ll_addpick);
        ivRecipePic = findViewById(R.id.iv_create1_recipe_pic);

        ivRecipePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();

                dataPic = Uri.parse(path);

                i.setDataAndType(dataPic, "image/*");

                startActivityForResult(i, IMAGE_GALLERY_REQUEST);
            }
        });

        llAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();

                dataPic = Uri.parse(path);

                i.setDataAndType(dataPic, "image/*");

                startActivityForResult(i, IMAGE_GALLERY_REQUEST);
            }
        });

        tvEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEasy.setTextColor(Color.parseColor("#f4f0ef"));
                tvEasy.setBackgroundColor(Color.parseColor("#FF9400"));

                tvMedium.setTextColor(Color.parseColor("#02043F"));
                tvMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvHard.setTextColor(Color.parseColor("#02043F"));
                tvHard.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Easy";
            }
        });

        tvMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMedium.setTextColor(Color.parseColor("#f4f0ef"));
                tvMedium.setBackgroundColor(Color.parseColor("#FF9400"));

                tvEasy.setTextColor(Color.parseColor("#02043F"));
                tvEasy.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvHard.setTextColor(Color.parseColor("#02043F"));
                tvHard.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Medium";
            }
        });

        tvHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHard.setTextColor(Color.parseColor("#f4f0ef"));
                tvHard.setBackgroundColor(Color.parseColor("#FF9400"));

                tvMedium.setTextColor(Color.parseColor("#02043F"));
                tvMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvEasy.setTextColor(Color.parseColor("#02043F"));
                tvEasy.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Hard";
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pb.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(etRecipeName.getText()) ||
                        TextUtils.isEmpty(etDescription.getText() )||
                                TextUtils.isEmpty(etCookingTime.getText())||
                                        TextUtils.isEmpty(etPrepTime.getText()) ||
                                                TextUtils.isEmpty(etServings.getText()) ||
                                                        dataPic == null){

                    if(TextUtils.isEmpty(etRecipeName.getText()))
                        etRecipeName.setError("Recipe Name is Required");


                    if(TextUtils.isEmpty(etDescription.getText()))
                        etDescription.setError("Description is Required");


                    if(TextUtils.isEmpty(etCookingTime.getText()))
                        etCookingTime.setError("Cooking Time is Required");


                    if(TextUtils.isEmpty(etPrepTime.getText()))
                        etPrepTime.setError("Prep Time is Required");


                    if(TextUtils.isEmpty(etServings.getText()))
                        etServings.setError("Servings is Required");


                    Toast.makeText(CreateRecipeActivity1.this, "Fill Up All Values", Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.GONE);
                }
                else{
                    String recipeName, cookingTime, prepTime, servings, description, category;

                    recipeName = etRecipeName.getText().toString().trim();
                    cookingTime = etCookingTime.getText().toString().trim();
                    prepTime = etPrepTime.getText().toString().trim();
                    servings = etServings.getText().toString().trim();
                    description = etDescription.getText().toString().trim();
                    category = spCategory.getSelectedItem().toString().trim();

                    Intent i = new Intent(CreateRecipeActivity1.this, CreateRecipeActivity2.class);

                    i.putExtra(KEY_RECIPENAME, recipeName);
                    i.putExtra(KEY_COOKINGTIME, cookingTime);
                    i.putExtra(KEY_DESCRIPTION, description);
                    i.putExtra(KEY_PREPTIME, prepTime);
                    i.putExtra(KEY_SERVINGS, servings);
                    i.putExtra(KEY_CATEGORY, category);
                    i.putExtra(KEY_DIFFICULTY, difficulty);
                    i.putExtra(KEY_IMAGEURI, dataPic.toString());
                    pb.setVisibility(View.GONE);

                    startActivityForResult(i, 1);
                    /*
                    try {

                        FileOutputStream stream = openFileOutput(filename, Context.MODE_PRIVATE);
                        image.compress(Bitmap.CompressFormat.PNG, 100, stream);

                        stream.close();
                        image.recycle();

                        Intent i = new Intent(CreateRecipeActivity1.this, CreateRecipeActivity2.class);

                        i.putExtra(KEY_RECIPENAME, recipeName);
                        i.putExtra(KEY_COOKINGTIME, cookingTime);
                        i.putExtra(KEY_DESCRIPTION, description);
                        i.putExtra(KEY_PREPTIME, prepTime);
                        i.putExtra(KEY_SERVINGS, servings);
                        i.putExtra(KEY_CATEGORY, category);
                        i.putExtra(KEY_DIFFICULTY, difficulty);
                        pb.setVisibility(View.GONE);

                        startActivityForResult(i, 1);

                    } catch (FileNotFoundException e) {
                        pb.setVisibility(View.GONE);
                        e.printStackTrace();
                    } catch (IOException e) {
                        pb.setVisibility(View.GONE);
                        e.printStackTrace();
                    }*/
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                dataPic = data.getData();

                Picasso.with(this).load(dataPic).into(ivRecipePic);
                llAddPic.setVisibility(View.GONE);
            }
            else{
                super.onActivityResult(requestCode, resultCode, data);
                finish();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }
    }
}