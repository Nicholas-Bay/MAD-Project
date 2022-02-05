package com.example.mad_project.fragments;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mad_project.R;
import com.example.mad_project.activities.ProductAddAdapter;
import com.example.mad_project.activities.ProductAddHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Use the application default credentials
    public class SellerAddFragment extends Fragment {
    Spinner spinner;
    RecyclerView productAddRecycler;
    FirebaseFirestore db;
    //button for testing
    Button button;


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
        //button settings
        db=FirebaseFirestore.getInstance();
        button=view.findViewById(R.id.product_add);
        button.setOnClickListener(click);

        return view;
    }
    private void productAddRecycler(){
        //default
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //slide
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //side by side
//        StaggeredGridLayoutManager lm=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager lm=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        productAddRecycler.setLayoutManager(lm);
        ArrayList<ProductAddHelper> productAdd = new ArrayList<>();
        productAdd.add(new ProductAddHelper(R.drawable.empty));
        productAdd.add(new ProductAddHelper(R.drawable.nicholas));
            productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));
        productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));
        productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));
        productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));
        productAdd.add(new ProductAddHelper(R.drawable.accesorieswatches));

        RecyclerView.Adapter adapter = new ProductAddAdapter(productAdd);
        productAddRecycler.setItemAnimator(new DefaultItemAnimator());
        productAddRecycler.setAdapter(adapter);
    }
    public View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String,Object>something=new HashMap<>();
            something.put("1",1);
            something.put("2",2);
            db.collection("user")
                    .add(something)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();

                }
            });
        }
    };

}