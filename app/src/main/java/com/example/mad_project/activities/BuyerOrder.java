package com.example.mad_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project.R;
import com.example.mad_project.fragments.BuyerHomeFragment;

public class BuyerOrder extends AppCompatActivity {

    Button Buttonhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order);
        Buttonhome = findViewById(R.id.Button_home_order);
        Buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new BuyerHomeFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_buyer_container,fragment).commit();
                Intent intent1 = new Intent(BuyerOrder.this, MainPageBuyerActivity.class);
                startActivity(intent1);

            }
        });
    }
}