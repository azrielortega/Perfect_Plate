package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreateRecipeActivity3 extends AppCompatActivity {

    private int stepCtr = 0;

    private Button btnAdd;
    private Button btnFinish;
    private LinearLayout llSteps;

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