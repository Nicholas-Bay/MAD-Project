package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mad_project.R;

public class HomeBuyerFragment extends Fragment {
    public HomeBuyerFragment() {
        super(R.layout.fragment_buyer_home);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_home, container, false);
        return view;
    }
}