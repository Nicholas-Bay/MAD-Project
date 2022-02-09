package com.example.mad_project.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mad_project.R;
import com.example.mad_project.activities.Ps5forms;
import com.example.mad_project.activities.walletsforms;

public class SellerHomeFragment extends Fragment {

    TextView devices,consumables,consoles,clothing,books,wallets;

    public SellerHomeFragment() {
        super(R.layout.fragment_seller_home);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_home, container, false);

        //Hooks
        devices=view.findViewById(R.id.devices_textview);
        consumables=view.findViewById(R.id.consumable_textView);
        consoles=view.findViewById(R.id.consoles_textview);
        clothing=view.findViewById(R.id.clothing_textview);
        books=view.findViewById(R.id.books_textview);
        wallets=view.findViewById(R.id.wallets_textview);

        wallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplication(), walletsforms.class);
                startActivity(intent1);
            }
        });

        consoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), Ps5forms.class);
                startActivity(intent2);
            }
        });

        return view;
    }
}