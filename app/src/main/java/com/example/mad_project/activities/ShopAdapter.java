package com.example.mad_project.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mad_project.R;
import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    ArrayList<ShopHelperClass> shopItems;
    Context context;

    public ShopAdapter(Context context, ArrayList<ShopHelperClass> shopItems) {
        this.shopItems = shopItems;
        //this.context = context;
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
        holder.image.setImageResource(shopHelperClass.getImage());
        holder.name.setText(shopHelperClass.getName());
        holder.price.setText(shopHelperClass.getPrice());
        holder.state.setText(shopHelperClass.getState());
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, state;
        FrameLayout shopItems;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shop_image);
            name = itemView.findViewById(R.id.shop_name);
            price = itemView.findViewById(R.id.shop_price);
            state = itemView.findViewById(R.id.shop_state);
            shopItems = itemView.findViewById(R.id.shop_recycler);
        }
    }
}