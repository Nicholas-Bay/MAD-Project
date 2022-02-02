package com.example.mad_project.activities;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;

import java.util.ArrayList;

public class ProductAddAdapter extends RecyclerView.Adapter<ProductAddAdapter.ProductAddHolder>{
    ArrayList<ProductAddHelper> productAdd;

    public ProductAddAdapter(ArrayList<ProductAddHelper> productAdd){
        this.productAdd = productAdd;
    }

    @NonNull
    @Override
    public ProductAddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_image,parent,false);
//         View view = View.inflate(parent.getContext(), R.drawable.empty,parent);
        ProductAddHolder productAddHolder = new ProductAddHolder(view);
        return productAddHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAddAdapter.ProductAddHolder holder, int position) {
        ProductAddHelper productAddHelper = productAdd.get(position);
        holder.image.setImageResource(productAddHelper.getImage());


    }

    @Override
    public int getItemCount() {
        return productAdd.size();
    }

    public static class ProductAddHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ProductAddHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.imageView9);
        }
    }
}
