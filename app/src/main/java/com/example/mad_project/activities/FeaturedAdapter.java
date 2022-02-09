package com.example.mad_project.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.mad_project.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredDesign;
    Context context;

    public FeaturedAdapter(Context context,ArrayList<FeaturedHelperClass> featuredDesign) {
        this.featuredDesign = featuredDesign;
        this.context = context;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass = featuredDesign.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.disc.setText(featuredHelperClass.getDescription());
        holder.featuredDesign.setOnClickListener(view -> {
            switch(position){
                case 0:
                    Intent intent = new Intent(context, Iphone13forms.class);
                    context.startActivity(intent);
                    break;
                case 1:
                    Intent intent1 = new Intent(context, Asuslaptopforms.class);
                    context.startActivity(intent1);
                    break;
                case 3:
                    Intent intent2 = new Intent(context, Samsungforms.class);
                    context.startActivity(intent2);
                    break;
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredDesign.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,disc;
        LinearLayout featuredDesign;



        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            disc = itemView.findViewById(R.id.featured_disc);
            featuredDesign = itemView.findViewById(R.id.featured_card_design);
        }
    }

}
