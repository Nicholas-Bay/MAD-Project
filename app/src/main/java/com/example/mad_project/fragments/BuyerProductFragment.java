package com.example.mad_project.fragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mad_project.R;
import com.example.mad_project.activities.MainPageBuyerActivity;

import android.widget.ImageView;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class BuyerProductFragment extends Fragment {
    //back button stuff
//    private FragmentManager fragmentManager;
//    private AnimatedBottomBar animatedBottomBar;
    ImageView backbutton;
    public BuyerProductFragment() {
        super(R.layout.fragment_buyer_product);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_product, container, false);
        //hooks
//        animatedBottomBar=view.findViewById(R.id.animatedBuyerBottomBar);
        backbutton = view.findViewById(R.id.back_product);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to home page
                Intent intent=new Intent(getActivity().getApplication(), MainPageBuyerActivity.class);
                startActivity(intent);
//            Fragment fragment=null;
//            fragment=new BuyerHomeFragment();
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_buyer_container,fragment,"findThisFragment")
//                    .addToBackStack(null)
//                    .commit();
//            animatedBottomBar.selectTabById(R.id.home_buyer,false);
            }
        });
        return view;
    }
}