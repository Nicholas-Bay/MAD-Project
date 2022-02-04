package com.example.mad_project.fragments;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mad_project.R;
import com.example.mad_project.activities.Applewatchforms;
import com.example.mad_project.activities.Clothingforms;
import com.example.mad_project.activities.Iphone13forms;
import com.example.mad_project.activities.MainPageBuyerActivity;
import com.example.mad_project.activities.Ps5forms;
import com.example.mad_project.activities.Samsungforms;

import android.widget.Button;
import android.widget.ImageView;

public class BuyerProductFragment extends Fragment {
    //back button stuff
//    private FragmentManager fragmentManager;
//    private AnimatedBottomBar animatedBottomBar;
    ImageView backbutton;
    Button iphonedetails, watchdetails , clothingdetails , samsungdetails , ps5details;
    public BuyerProductFragment() {
        super(R.layout.fragment_buyer_product);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_product, container, false);
        //hooks
        //animatedBottomBar=view.findViewById(R.id.animatedBuyerBottomBar);
        backbutton = view.findViewById(R.id.back_product);
        iphonedetails = view.findViewById(R.id.iphone_webpage);
        watchdetails = view.findViewById(R.id.watch_webpage);
        clothingdetails = view.findViewById(R.id.clothing_webpage);
        samsungdetails = view.findViewById(R.id.s21_webpage);
        ps5details = view.findViewById(R.id.ps5_webpage);



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to home page
                Intent intent=new Intent(getActivity().getApplication(), MainPageBuyerActivity.class);
                startActivity(intent);
                //Fragment fragment=null;
                //fragment=new BuyerHomeFragment();
                //getActivity().getSupportFragmentManager().beginTransaction()
                //.replace(R.id.fragment_buyer_container,fragment,"findThisFragment")
                //.addToBackStack(null)
                //.commit();
                //animatedBottomBar.selectTabById(R.id.home_buyer,false);
            }
        });

        iphonedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplication(), Iphone13forms.class);
                startActivity(intent1);
            }
        });

        watchdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), Applewatchforms.class);
                startActivity(intent2);
            }
        });

        clothingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity().getApplication(), Clothingforms.class);
                startActivity(intent3);
            }
        });

        samsungdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity().getApplication(), Samsungforms.class);
                startActivity(intent4);
            }
        });

        ps5details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity().getApplication(), Ps5forms.class);
                startActivity(intent5);
            }
        });




        return view;
    }
}