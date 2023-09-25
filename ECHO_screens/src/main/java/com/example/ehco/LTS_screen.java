package com.example.ehco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LTS_screen extends AppCompatActivity {

    //button for screen transition from MainActivity -> LTS_screen
    private Button button;
    Dialog showDialog;
    private EditText InputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lts_screen);

        // initializing the value of EditText and getting the Input values to store in a variable
        InputEditText = findViewById(R.id.WordToTranslate_text);


        //Code for screen transition
        button = (Button) findViewById(R.id.Translate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToLoadingScreen();

            }
        });

        //Code for screen contact info
        showDialog = new Dialog(this);

    }




    public void openDialog(View view) {
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

    public void ToLoadingScreen (){

        // get the user's input and transform it into char array
        String InputText = InputEditText.getText().toString().trim();
        char [] InputLetterArray = InputText.toLowerCase().toCharArray();

        // passing input values to another activity
        Intent intent = new Intent(LTS_screen.this, Loading_screenn.class);
        intent.putExtra("OutputText", InputText);

        // adding all elements of InputLetterArray to InputLetterList
        ArrayList<String> InputLetterList = new ArrayList<>();
        for (int i = 0; i < InputLetterArray.length; i++) {
            InputLetterList.add(String.valueOf(InputLetterArray[i]));
        }

        // creating a list that contains all the drawable ids of the individual characters in the user's inputted word
        ArrayList<Integer> LetterIds = new ArrayList<>();
        for (int i = 0; i < InputLetterList.size() ; i++) {
            LetterIds.add(getResources().getIdentifier(InputLetterList.get(i).trim(),"drawable",getPackageName()));
        }

        // passing the list of ids in another activity then switch next activity afterwards
        intent.putExtra("DrawableIds", LetterIds);

        // validate the input of the user if only contains alphabets
        boolean check = validateInput(InputText);

        if (check == true) {
            Toast.makeText(getApplicationContext(),"Please wait", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter valid values", Toast.LENGTH_SHORT).show();
        }



    }

    private Boolean validateInput(String InputText)
    {
        if(InputText.length() == 0 )
        {
            InputEditText.requestFocus();
            InputEditText.setError("Please Enter a Word");
            return false;
        }
        else if (!InputText.matches("[a-zA-Z]+"))
        {
            InputEditText.requestFocus();
            InputEditText.setError("Enter Alphabetical Character only");
            return false;
        }
        else if(InputText.length() > 12)
        {
            InputEditText.requestFocus();
            InputEditText.setError("Exceeded character limit: 12");
            return false;
        }

        else
        {
            return true;
        }
    }




}