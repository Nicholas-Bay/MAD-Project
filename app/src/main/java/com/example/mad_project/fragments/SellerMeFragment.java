package com.example.mad_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mad_project.R;

public class SellerMeFragment extends Fragment {
    public SellerMeFragment() {
        super(R.layout.fragment_seller_me);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_me, container, false);
        RatingBar ratingBar=view.findViewById(R.id.ratingBar);
        ratingBar.setRating(3);
        ratingBar.setIsIndicator(true);
        //redemptyion things
        LinearLayout sellerMeRedemption1=view.findViewById(R.id.seller_me_redemption1);
        LinearLayout sellerMeRedemption2=view.findViewById(R.id.seller_me_redemption2);
        LinearLayout sellerMeRedemption3=view.findViewById(R.id.seller_me_redemption3);
        TextView sellerMePoint1=view.findViewById(R.id.seller_me_points1);
        TextView sellerMePoint2=view.findViewById(R.id.seller_me_points2);
        TextView sellerMePoint3=view.findViewById(R.id.seller_me_points3);
        TextView sellerMeText1=view.findViewById(R.id.seller_me_text1);
        TextView sellerMeText2=view.findViewById(R.id.seller_me_text2);
        TextView sellerMeText3=view.findViewById(R.id.seller_me_text3);

        sellerMeRedemption1.setOnClickListener(onClick1);
        sellerMeText1.setOnClickListener(onClick1);
        sellerMePoint1.setOnClickListener(onClick1);
        sellerMeRedemption2.setOnClickListener(onClick2);
        sellerMeText2.setOnClickListener(onClick2);
        sellerMePoint2.setOnClickListener(onClick2);
        sellerMeRedemption3.setOnClickListener(onClick3);
        sellerMeText3.setOnClickListener(onClick3);
        sellerMePoint3.setOnClickListener(onClick3);



    return view;
    }

    private View.OnClickListener onClick1=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Toast.makeText(getActivity(), "redeme free bumps", Toast.LENGTH_SHORT).show();

        }
    };
    private View.OnClickListener onClick2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "redeme free bumps2", Toast.LENGTH_SHORT).show();

        }
    };
    private View.OnClickListener onClick3=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "redeme free bumps3", Toast.LENGTH_SHORT).show();

        }
    };

}
