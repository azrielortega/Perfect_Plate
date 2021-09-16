package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLOutput;

public class WriteReviewActivity extends AppCompatActivity {

    private ImageButton ibSubmit;
    private ImageButton ibBack;
    private RatingBar rbRating;
    private EditText etReview;

    private ImageView ivReviewPic;

    private LinearLayout llAddPic;
    private Uri imageUri;
    private StorageReference storageRef;
    private UploadImage upload;


    private Boolean submitted = false;
    public static final String KEY_SUBMITTED = "KEY_SUBMITTED";
    public static final int IMAGE_GALLERY_REQUEST = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        this.ibSubmit = findViewById(R.id.ib_review_submit);
        this.ibBack = findViewById(R.id.ib_review_back);
        this.rbRating = findViewById(R.id.rb_rating);
        this.etReview = findViewById(R.id.et_review);
        this.llAddPic = findViewById(R.id.ll_add_photo);
        this.ivReviewPic = findViewById(R.id.iv_review_pic);

        Intent i = getIntent();
        String recipeId = i.getStringExtra("KEY_RECIPE_ID");
        storageRef = FirebaseStorage.getInstance().getReference("uploads");

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.ibSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = rbRating.getRating();
                String reviewText = etReview.getText().toString();
                ibSubmit.setEnabled(false);
                

//                Review review = new Review(DataHelper.user, rating, reviewText, recipeId);
//
//                DataHelper.reviewDatabase.addReview(review);
//                finish();

                if (imageUri != null) {
                    String picUrl = imageUri.toString();
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

                                upload = new UploadImage("recipeImage", downloadURL);

                                Review review = new Review(DataHelper.user, rating, reviewText, recipeId, upload);
                                review.setUploadImage(upload);

                                DataHelper.reviewDatabase.addReview(review);

                                Toast.makeText(WriteReviewActivity.this, "Added Review", Toast.LENGTH_SHORT).show();

                                finish();
                            } else {
                                // Handle failures
                                // ...
                                Toast.makeText(WriteReviewActivity.this, "FAIL ADDING REVIEW", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    Review review = new Review(DataHelper.user, rating, reviewText, recipeId);
                    DataHelper.reviewDatabase.addReview(review);
                    Toast.makeText(WriteReviewActivity.this, "Added Review", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

        llAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();

                imageUri = Uri.parse(path);

                i.setDataAndType(imageUri, "image/*");

                startActivityForResult(i, IMAGE_GALLERY_REQUEST);
            }
        });
        ivReviewPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();

                imageUri = Uri.parse(path);

                i.setDataAndType(imageUri, "image/*");

                startActivityForResult(i, IMAGE_GALLERY_REQUEST);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                imageUri = data.getData();

                Picasso.with(this).load(imageUri).into(ivReviewPic);
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

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

}