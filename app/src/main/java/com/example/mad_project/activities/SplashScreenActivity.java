package com.example.mad_project.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mad_project.R;

public class SplashScreenActivity extends AppCompatActivity {
    //variables
    Animation topAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        //Hooks
        image = findViewById(R.id.ImageView);

        image.setAnimation(topAnim);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity((new Intent(SplashScreenActivity.this, LoginActivity.class)));
            finish();
        }, 2000);
    }
}