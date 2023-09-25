package com.example.ehco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading_screenn extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screenn);
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent newIntent = new Intent(Loading_screenn.this, Translate_screen.class);
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    newIntent.putExtras(bundle);
                }
                startActivity(newIntent);
                finish();

            }
        }, 3000);

    }
}