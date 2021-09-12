package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

public class CreateRecipeActivity3 extends AppCompatActivity {

    private int stepCtr = 0;

    private Button btnAdd;
    private Button btnFinish;
    private LinearLayout llSteps;
    private ImageButton ibBack;

    private String recipeName, description, category, difficulty;
    private int cookingTime, prepTime, servings;
    private ArrayList<Ingredient> ingredients;
    private Bitmap image;

    private StorageReference storageRef;

    private Uri imageUri;

    private UploadImage upload;

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrecipe3);

        storageRef = FirebaseStorage.getInstance().getReference("uploads");

        Intent tempI = getIntent();

        recipeName = tempI.getStringExtra(CreateRecipeActivity1.KEY_RECIPENAME);
        cookingTime = Integer.parseInt(tempI.getStringExtra(CreateRecipeActivity1.KEY_COOKINGTIME));
        prepTime = Integer.parseInt(tempI.getStringExtra(CreateRecipeActivity1.KEY_PREPTIME));
        servings = Integer.parseInt(tempI.getStringExtra(CreateRecipeActivity1.KEY_SERVINGS));
        description =tempI.getStringExtra(CreateRecipeActivity1.KEY_DESCRIPTION);
        ingredients = (ArrayList<Ingredient>) tempI.getSerializableExtra(CreateRecipeActivity2.KEY_INGREDIENTS);
        difficulty = tempI.getStringExtra(CreateRecipeActivity1.KEY_DIFFICULTY);
        category =tempI.getStringExtra(CreateRecipeActivity1.KEY_CATEGORY);
        String tempImageUri = tempI.getStringExtra(CreateRecipeActivity1.KEY_IMAGEURI);

        imageUri = Uri.parse(tempImageUri);

        /*
        try{
            FileInputStream is = openFileInput(CreateRecipeActivity1.filename);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        this.initComponents();
    }

    private void initComponents() {

        pb = findViewById(R.id.pb_create3);

        this.btnAdd = findViewById(R.id.createrecipe3_btn_add);
        this.llSteps = findViewById(R.id.createrecipe3_ll_steps);
        btnFinish = findViewById(R.id.createrecipe3_btn_finish);
        ibBack = findViewById(R.id.createrecipe3_ib_back);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFinish.setEnabled(false);
                pb.setVisibility(View.VISIBLE);
                ArrayList<String> steps = new ArrayList<>();

                final int stepCount = llSteps.getChildCount();

                if(stepCount != 0) {
                    boolean flag = false;

                    for (int i = 0; i < stepCount; i++) {
                        View tempV = llSteps.getChildAt(i);
                        EditText etStep = tempV.findViewById(R.id.template_cr_steps_et_step);

                        if(TextUtils.isEmpty(etStep.getText())){

                            if(TextUtils.isEmpty(etStep.getText()))
                                etStep.setError("Step " + String.valueOf(i + 1) + " is Required");

                            flag = true;
                        }
                        else{
                            String step = etStep.getText().toString().trim();
                            steps.add(step);
                        }
                    }

                    if(!flag) {
                        RecipeDatabase db = new RecipeDatabase();
                        Recipe recipe = new Recipe(R.drawable.adobo, recipeName, 0, 0, "108649384933214190699",
                                description, 0, cookingTime, prepTime, servings, category, difficulty);

                        //adding steps to realtime database
                        recipe.addStepsList(steps);

                        //adding ingredients to realtime database
                        for (int i = 0; i < ingredients.size(); i++) {
                            recipe.addIngredient(ingredients.get(i));
                        }
                        //adding image to storage and add recipe to real database
                        uploadFile(recipe, db);
                    }
                    else{
                        Toast.makeText(CreateRecipeActivity3.this, "Fill Up All Values", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        btnFinish.setEnabled(true);
                    }
                }
                else{
                    Toast.makeText(CreateRecipeActivity3.this, "Add at least 1 Step", Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.GONE);
                    btnFinish.setEnabled(true);
                }
            }
        });


        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stepCtr++;

                View stepLayout = getLayoutInflater().inflate(R.layout.createrecipe_steps_template, llSteps, false);
                TextView tvTemp = stepLayout.findViewById(R.id.template_cr_steps_tv_num);
                tvTemp.setText(Integer.toString(stepCtr));

                ImageView ivDelete = stepLayout.findViewById(R.id.template_cr_steps_iv_delete);

                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (stepCtr > 0){
                            stepCtr --;
                            TextView tvNum = stepLayout.findViewById(R.id.template_cr_steps_tv_num);
                            llSteps.removeView(stepLayout);

                            for (int i = 0; i < llSteps.getChildCount(); i++){
                                View tempV = llSteps.getChildAt(i);

                                TextView tvStepNumber = tempV.findViewById(R.id.template_cr_steps_tv_num);
                                tvStepNumber.setText(String.valueOf(i + 1));
                            }
                        }
                    }
                });

                llSteps.addView(stepLayout);
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile(Recipe recipe, RecipeDatabase db){

        if (imageUri != null){
            final StorageReference fileRef = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            UploadTask uploadTask = fileRef.putFile(imageUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String downloadURL = downloadUri.toString();

                        upload = new UploadImage ("recipeImage", downloadURL);
                        recipe.setUploadImage(upload);

                        //upload everything to DB;
                        db.addRecipe(recipe);
                        Toast.makeText(CreateRecipeActivity3.this, "Added Recipe", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        btnFinish.setEnabled(true);
                        finish();
                    } else {
                        // Handle failures
                        // ...
                        Toast.makeText(CreateRecipeActivity3.this, "FAIL ADDING RECIPE", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        btnFinish.setEnabled(true);
                    }
                }
            });
        }else{
            Toast.makeText(CreateRecipeActivity3.this, "No Image Selected", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
            btnFinish.setEnabled(true);
        }

    }
}