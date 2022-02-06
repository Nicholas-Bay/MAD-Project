package com.example.mad_project.fragments;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mad_project.R;
import com.example.mad_project.activities.CategoriesAdapter;
import com.example.mad_project.activities.CategoriesHelperClass;
import com.example.mad_project.activities.Electronicstbpage;
import com.example.mad_project.activities.FeaturedAdapter;
import com.example.mad_project.activities.FeaturedHelperClass;
import com.example.mad_project.activities.Foodtbpage;
import com.example.mad_project.activities.mostViewedAdapter;
import com.example.mad_project.activities.mostViewedHelperClass;

import java.util.ArrayList;

public class BuyerHomeFragment extends Fragment {
    RecyclerView featuredRecycler;
    RecyclerView mostviewedRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView toolfood, tooltech;
    public BuyerHomeFragment() {
        super(R.layout.fragment_buyer_home);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hooks
        featuredRecycler = view.findViewById(R.id.featured_recycler);
        mostviewedRecycler = view.findViewById(R.id.mostviewed_recycler);
        categoriesRecycler = view.findViewById(R.id.categories_recycler);
        toolfood = view.findViewById(R.id.collapse_food);
        tooltech = view.findViewById(R.id.collapse_tech);

        //running toolbar for food
        toolfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Foodtbpage.class);
                startActivity(intent);
            }
        });

        //running toolbar for tech
        tooltech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Electronicstbpage.class);
                startActivity(intent1);
            }
        });

        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
        return view;
    }

    private void featuredRecycler(){
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredDesigns = new ArrayList<>();

        featuredDesigns.add(new FeaturedHelperClass(R.drawable.iphone,"Iphone 13 Pro Max","One of The Top Apple Products of The Year "));
        featuredDesigns.add(new FeaturedHelperClass(R.drawable.asus,"Asus Laptop ","One of The Top Laptop of The Year "));
        featuredDesigns.add(new FeaturedHelperClass(R.drawable.s21,"Samsung S21 Ultra","One of The Top Android Phone of The Year "));

        adapter = new FeaturedAdapter(featuredDesigns);
        featuredRecycler.setAdapter(adapter);

        //featuredRecycler.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private void mostViewedRecycler(){
        mostviewedRecycler.setHasFixedSize(true);
        mostviewedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<mostViewedHelperClass> mostViewedDesigns = new ArrayList<>();

        mostViewedDesigns.add(new mostViewedHelperClass(R.drawable.iphone,"Iphone 13 Pro Max","One of the Top Phones"));
        mostViewedDesigns.add(new mostViewedHelperClass(R.drawable.applewatch,"Apple Watch Series 7","One of the Best Watches"));
        mostViewedDesigns.add(new mostViewedHelperClass(R.drawable.asus,"Asus Zenbook","One of the Top Laptops"));
        mostViewedDesigns.add(new mostViewedHelperClass(R.drawable.s21,"Samsung S21 Ultra","Top Phones of the year "));
        mostViewedDesigns.add(new mostViewedHelperClass(R.drawable.nicholas,"Nicholas Bay","Can Programme For you :)"));





        adapter = new mostViewedAdapter(mostViewedDesigns);
        mostviewedRecycler.setAdapter(adapter);
    }

    private void categoriesRecycler(){
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<CategoriesHelperClass> categoriesDesign = new ArrayList<>();

        categoriesDesign.add(new CategoriesHelperClass(R.drawable.accesorieswatches,"Accessories"));
        categoriesDesign.add(new CategoriesHelperClass(R.drawable.phonescat,"Electronic devices"));
        categoriesDesign.add(new CategoriesHelperClass(R.drawable.clothing,"Clothing"));
        categoriesDesign.add(new CategoriesHelperClass(R.drawable.consolescat,"Consoles"));


        adapter = new CategoriesAdapter(categoriesDesign);
        categoriesRecycler.setAdapter(adapter);
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }
}