package com.example.mad_project.fragments;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.activities.CategoriesAdapter;
import com.example.mad_project.activities.CategoriesHelperClass;
import com.example.mad_project.activities.ProductAddAdapter;
import com.example.mad_project.activities.ProductAddHelper;

import java.util.ArrayList;

public class SellerAddFragment extends Fragment {
    Spinner spinner;
    TextView title;
    Drawable background;
    BitmapDrawable reSizedBackground;
    RecyclerView productAddRecycler;
    RecyclerView.Adapter adapter;

    public SellerAddFragment() {
        super(R.layout.fragment_seller_add);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_add, container, false);
        //spinner settings
        spinner=view.findViewById(R.id.product_category);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getActivity(),R.array.category,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //recycler view settings
        productAddRecycler=view.findViewById(R.id.product_addPhoto);
        productAddRecycler();

        return view;
    }
    private void productAddRecycler(){
        productAddRecycler.setHasFixedSize(true);
        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ArrayList<ProductAddHelper> productAdd = new ArrayList<>();
        productAdd.add(new ProductAddHelper(R.drawable.empty));
        productAdd.add(new ProductAddHelper(R.drawable.nicholas));
        productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));


        adapter = new ProductAddAdapter(productAdd);
        productAddRecycler.setAdapter(adapter);
    }
}