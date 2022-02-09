package com.example.mad_project.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;
import com.example.mad_project.activities.BuyerCart;
import com.example.mad_project.activities.Googleforms;
import com.example.mad_project.activities.ShopAdapter;
import com.example.mad_project.activities.ShopHelperClass;

import java.util.ArrayList;

public class BuyerShopFragment extends Fragment {
    RecyclerView shopRecycler;
    RecyclerView.Adapter adapter;
    ImageView cartCheckout;
    public BuyerShopFragment() {
        super(R.layout.fragment_buyer_shop);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_shop, container, false);
        shopRecycler = view.findViewById(R.id.shop_recycler);
        cartCheckout = view.findViewById(R.id.cart_checkout);

        //running cart
        cartCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplication(), BuyerCart.class);
                startActivity(intent1);

            }
        });
        shopRecycler();
        return view;
    }
    private void shopRecycler(){
        shopRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        shopRecycler.setLayoutManager(layoutManager);

        ArrayList<ShopHelperClass> shopItems = new ArrayList<>();

        shopItems.add(new ShopHelperClass(R.drawable.applewatch, "Apple Watch Series 7", "$500", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.iphone, "iPhone 13 Pro Max", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.airpods_max, "Airpods Max", "$600", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.airpods_pro, "Airpods Pro", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.mbp, "Macbook Pro", "$1900", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.ipad_pro, "Ipad Pro", "$800", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.iphonese, "Iphone SE", "$600", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.applecharger, "Apple 25W charger", "$800", "Available"));
        shopItems.add(new ShopHelperClass(R.drawable.macbookairm2, "Macbook Air M2", "$1200", "UnAvailable"));
        shopItems.add(new ShopHelperClass(R.drawable.ipadmini, "Ipad mini", "$700", "Available"));


        adapter = new ShopAdapter(getContext(),shopItems);
        shopRecycler.setAdapter(adapter);

    }
}