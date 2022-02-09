package com.example.mad_project.fragments;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.R;
import com.example.mad_project.activities.BuyerCart;

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

        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.applewatch)
                , "Apple Watch Series 7", "$500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.iphone)
                , "iPhone 13 Pro Max", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.airpods_max)
                , "Airpods Max", "$600", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.airpods_pro)
                , "Airpods Pro", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.mbp)
                , "Macbook Pro", "$1900", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.ipad_pro)
                , "Ipad Pro", "$800", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.iphonese)
                , "Iphone SE", "$600", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.applecharger)
                , "Apple 25W charger", "$800", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.macbookairm2)
                , "Macbook Air M2", "$1200", "UnAvailable"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.ipadmini)
                , "Ipad mini", "$700", "Available"));


        adapter = new ShopAdapter(getContext(),shopItems);
        shopRecycler.setAdapter(adapter);

    }

    public static class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

        ArrayList<ShopHelperClass> shopItems;
        Context context;

        public ShopAdapter(Context context, ArrayList<ShopHelperClass> shopItems) {
            this.shopItems = shopItems;
            this.context = context;
            //hi
        }

        @NonNull
        @Override
        public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_row,parent,false);
            ShopViewHolder shopViewHolder = new ShopViewHolder(view);
            return shopViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
            ShopHelperClass shopHelperClass = shopItems.get(position);
            holder.image.setImageDrawable(shopHelperClass.getImage());
            holder.name.setText(shopHelperClass.getName());
            holder.price.setText(shopHelperClass.getPrice());
            holder.state.setText(shopHelperClass.getState());
            holder.incrementQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp=Integer.parseInt(holder.valueQty.getText().toString());
                    temp++;
                    holder.valueQty.setText(String.valueOf(temp));
                }
            });
            holder.decrementQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp=Integer.parseInt(holder.valueQty.getText().toString());
                    temp--;
                    if(temp<=0)temp=0;
                    holder.valueQty.setText(String.valueOf(temp));
                }
            });
            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"addedToCart",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return shopItems.size();
        }

        public static class ShopViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView name, price, state;
            FrameLayout shopItems;
            Button decrementQty , incrementQty, addToCart;
            EditText valueQty;

            public ShopViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.shop_image);
                name = itemView.findViewById(R.id.shop_name);
                price = itemView.findViewById(R.id.shop_price);
                state = itemView.findViewById(R.id.shop_state);
                shopItems = itemView.findViewById(R.id.shop_recycler);
                decrementQty=itemView.findViewById(R.id.decrement);
                incrementQty=itemView.findViewById(R.id.increment);
                valueQty=itemView.findViewById(R.id.value);
                addToCart=itemView.findViewById(R.id.addToCartButton);

            }

            public EditText getValueQty() {
                return valueQty;
            }
        }
    }

    public static class ShopHelperClass {
        Drawable image;
        String name, price, state;

        public ShopHelperClass(Drawable image, String name, String price, String state) {
            this.image = image;
            this.name = name;
            this.price = price;
            this.state = state;
        }

        public Drawable getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getState() {
            return state;
        }
    }

}