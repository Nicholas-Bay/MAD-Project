package com.example.mad_project.activities;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
//        holder.image.setImageResource(position);


    }

    @Override
    public int getItemCount() {
        return productAdd.size();
    }

    public class ProductAddHolder extends RecyclerView.ViewHolder{

        ImageView image;
        Drawable drawable;

        public ProductAddHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.empty);
            drawable=image.getDrawable();
        }
    }
}
