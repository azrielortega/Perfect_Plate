package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateRecipeActivity3 extends AppCompatActivity {

    private int stepCtr = 0;

    private Button btnAdd;
    private Button btnFinish;
    private LinearLayout llSteps;
    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrecipe3);

        this.initComponents();
    }

    private void initComponents() {

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
                finish();
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
                            //Toast.makeText(CreateRecipeActivity3.this, String.valueOf(start), Toast.LENGTH_SHORT).show();
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
}