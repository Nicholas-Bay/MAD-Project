package com.example.mad_project.activities;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mad_project.R;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> categoriesDesign;
    Context context;

    public CategoriesAdapter(Context context,ArrayList<CategoriesHelperClass> categoriesDesign){
        this.categoriesDesign = categoriesDesign;
        this.context=context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_design,parent,false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoriesHelperClass categoriesHelperClass = categoriesDesign.get(position);
        holder.image.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());
        holder.categoriesDesign.setOnClickListener(view ->{
            switch(position){
                case 0:
                    Intent intent = new Intent(context, ShopeeAccesories.class);
                    context.startActivity(intent);
                    break;
                case 1:
                    Intent intent1 = new Intent(context, ShopeeElectronics.class);
                    context.startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(context, ShopeeClothing.class);
                    context.startActivity(intent2);
                    break;

            }

        });


    }

    @Override
    public int getItemCount() {
        return categoriesDesign.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        RelativeLayout categoriesDesign;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.categories_image);
            title = itemView.findViewById(R.id.categoreis_title);
            categoriesDesign = itemView.findViewById(R.id.categories_card_design);

        }
    }
}
