package com.example.ehco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home_screenn extends AppCompatActivity {

    //button for screen transition from MainActivity -> LTS_screen
    private long backPressedTime;
    private Toast backToast;
    private ImageButton Imagebutton;
    Dialog showDialog;
    Dialog showSTLDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screenn);

        //Code for screen transition
        Imagebutton = (ImageButton) findViewById(R.id.LTS_button_);
        Imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLTS_screen();
            }
        });

        //Code for screen contact info
        showDialog = new Dialog(this);
        //Code for Sign Language to Translate Screen
        showSTLDialog = new Dialog(this);
    }
    public void openLTS_screen()
    {
        Intent intent = new Intent(this, LTS_screen.class);
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
    public void openShowSTLDialog(View view){
        TextView txtSTLclose;
        showSTLDialog.setContentView(R.layout.stl_screen);
        showSTLDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_round2);
        txtSTLclose = (TextView) showSTLDialog.findViewById(R.id.exitSTL_button);
        txtSTLclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSTLDialog.dismiss();
            }
        });
        showSTLDialog.show();
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 >System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast = Toast.makeText(getBaseContext(), "Press again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}