package com.example.mad_project.activities;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mad_project.R;
import com.example.mad_project.fragments.AddSellerFragment;
import com.example.mad_project.fragments.HomeSellerFragment;
import com.example.mad_project.fragments.MeSellerFragment;
import com.example.mad_project.fragments.ReputationSellerFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainPageSellerActivity extends AppCompatActivity {
    private static final String TAG = MainPageSellerActivity.class.getSimpleName();
    private AnimatedBottomBar animatedBottomBar;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_mainpage);
        getSupportActionBar().hide();
        animatedBottomBar = findViewById(R.id.animatedSellerBottomBar);
        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.home_seller, true);
            fragmentManager = getSupportFragmentManager();
            HomeSellerFragment homeSellerFragment = new HomeSellerFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_seller_container, homeSellerFragment).commit();
        }
        animatedBottomBar.setOnTabSelectListener((lastIndex, lastTab, newIndex, newTab) -> {
            Fragment fragment = null;
            switch (newTab.getId()) {
                case R.id.home_seller:
                    fragment = new HomeSellerFragment();
                    break;
                case R.id.reputation_seller:
                    fragment = new ReputationSellerFragment();
                    break;
                case R.id.add_seller:
                    fragment = new AddSellerFragment();
                    break;
                case R.id.me_seller:
                    fragment = new MeSellerFragment();
                    break;
            }
            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_seller_container, fragment).commit();
            } else {
                Log.e(TAG, "Error in creating Fragment");
            }
        });
    }
}
