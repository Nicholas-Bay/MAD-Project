package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mad_project.R;

public class BuyerReputationFragment extends Fragment {
    public BuyerReputationFragment() {
        super(R.layout.fragment_buyer_reputation);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_reputation, container, false);
        return view;
    }
}