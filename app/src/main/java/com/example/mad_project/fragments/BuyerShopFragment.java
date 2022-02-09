package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;
import com.example.mad_project.activities.ShopAdapter;
import com.example.mad_project.activities.ShopHelperClass;

import java.util.ArrayList;

public class BuyerShopFragment extends Fragment {
    RecyclerView shopRecycler;
    RecyclerView.Adapter adapter;
    public BuyerShopFragment() {
        super(R.layout.fragment_buyer_shop);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_shop, container, false);
        shopRecycler = view.findViewById(R.id.shop_recycler);
        shopRecycler();
        return view;
    }
    private void shopRecycler(){
        shopRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        shopRecycler.setLayoutManager(layoutManager);

        ArrayList<ShopHelperClass> shopItems = new ArrayList<>();

        shopItems.add(new ShopHelperClass(R.drawable.applewatch, "Apple Watch Series 6", "$500", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.iphone, "iPhone 13 Pro Max", "$1500", "Available"));

        adapter = new ShopAdapter(getContext(),shopItems);
        shopRecycler.setAdapter(adapter);

    }
}