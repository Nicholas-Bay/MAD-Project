package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mad_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class Foodtbpage extends AppCompatActivity {

    //Hooks
    TextToSpeech textspeech;
    FloatingActionButton speak;
    TextView fooddesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtbpage);
        speak = findViewById(R.id.text2speech);
        fooddesc = findViewById(R.id.collapse_food_desc);

        textspeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = textspeech.setLanguage(Locale.ENGLISH);
                    speak.setEnabled(true);
                }
                else {
                    Log.e("TTS","Initialization failed");
                }
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak1();
            }
        });
    }
    private void speak1(){
        String Text = fooddesc.getText().toString();
        textspeech.speak(Text,TextToSpeech.QUEUE_ADD,null);
    }
}