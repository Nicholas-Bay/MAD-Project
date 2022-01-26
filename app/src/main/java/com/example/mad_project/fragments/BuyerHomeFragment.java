package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.mad_project.R;
import com.example.mad_project.activities.FeaturedAdapter;
import com.example.mad_project.activities.FeaturedHelperClass;

import java.util.ArrayList;

public class BuyerHomeFragment extends Fragment {
    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    public BuyerHomeFragment() {
        super(R.layout.fragment_buyer_home);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hooks
        featuredRecycler = view.findViewById(R.id.featured_recycler);

        featuredRecycler();
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
}