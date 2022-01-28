package com.example.mad_project.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mad_project.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mostViewedAdapter extends RecyclerView.Adapter<mostViewedAdapter.MostViewHolder>{

    ArrayList<mostViewedHelperClass> mostViewedDesign;
    public mostViewedAdapter(ArrayList<mostViewedHelperClass> mostViewedDesign) {
        this.mostViewedDesign = mostViewedDesign;
    }

    @NonNull
    @Override
    public MostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mostviewed_design,parent,false);
        MostViewHolder mostViewHolder = new MostViewHolder(view);
        return mostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewHolder holder, int position) {
        mostViewedHelperClass MostViewedHelperClass = mostViewedDesign.get(position);

        holder.image.setImageResource(MostViewedHelperClass.getImage());
        holder.title.setText(MostViewedHelperClass.getTitle());
        holder.desc.setText(MostViewedHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewedDesign.size();
    }

    public static class MostViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;

        public MostViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.iphone_mostviewed);
            title = itemView.findViewById(R.id.iphone_mostviewed_title);
            desc = itemView.findViewById(R.id.iphone_mostviewed_desc);

        }
    }



}
