package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;

public class BuyerCalendarFragment extends Fragment {
    public BuyerCalendarFragment() {
        super(R.layout.fragment_buyer_calendar);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_calendar, container, false);
        Button button = view.findViewById(R.id.calendar_button);
        button.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "It fucking works", Toast.LENGTH_SHORT).show();
            TextView textView = view.findViewById(R.id.textVieww);
            textView.setText("WORKSSS");
        });
        return view;
    }
}