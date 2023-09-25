package com.example.ehco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.primitives.Ints;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.lang.String;

public class Translate_screen extends AppCompatActivity {

    SliderView sliderView;

    private Button button;
    Dialog showDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_screen);

        // getting the values from the first Activity
        ArrayList<Integer> LetterIdList = getIntent().getIntegerArrayListExtra("DrawableIds");
        String WordResult = getIntent().getStringExtra("OutputText");

        // converting the value of the list to int array
        int[] LetterImageIds = Ints.toArray(LetterIdList);
        TextView InputWord = findViewById(R.id.Result_text);

        // displaying the user's inputted word in Output Screen's Textview
        InputWord.setText(WordResult);

        // calling the function to create the Image Slider
        CreateImageSlide(LetterImageIds);


        button = (Button) findViewById(R.id.Home_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackHomeActivity();
            }
        });
        //Code for screen contact info
        showDialog = new Dialog(this);
    }
    private void CreateImageSlide(int[] LetterImageIds) {

        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(LetterImageIds);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
    }
    public void goBackHomeActivity(){
        Intent intent = new Intent(this, Home_screenn.class);
        startActivity(intent);
        finish();
    }
    public void openDialog(View view){
        TextView txtclose;
        showDialog.setContentView(R.layout.about_us);
        showDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_round);
        txtclose = (TextView) showDialog.findViewById(R.id.exit_button);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });
        showDialog.show();
    }

}