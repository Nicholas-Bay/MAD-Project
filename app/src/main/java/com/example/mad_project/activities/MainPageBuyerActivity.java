package com.example.mad_project.activities;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.mad_project.R;
import com.example.mad_project.fragments.BuyerShopFragment;
import com.example.mad_project.fragments.BuyerHomeFragment;
import com.example.mad_project.fragments.BuyerMeFragment;
import com.example.mad_project.fragments.BuyerProductFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainPageBuyerActivity extends AppCompatActivity {
    private static final String TAG = MainPageBuyerActivity.class.getSimpleName();
    private AnimatedBottomBar animatedBottomBar;
    private FragmentManager fragmentManager;
    //firebasestuff firestore
    FirebaseFirestore db;
    ArrayList<ProductList> productListArrayList=new ArrayList<>();
    //photo storage
    FirebaseStorage storage;
    StorageReference storageReference;
    //bundle to pass activity to fragment
    Bundle bundle=new Bundle();
    Bundle fireStoreBundle=new Bundle();
    Bundle combinedBundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_mainpage);
        getSupportActionBar().hide();
        //getting data from loginActivity and storing into bundle
        //so that we can pass bundle to the fragments
        Intent intent=getIntent();
        bundle.putString("username",intent.getStringExtra("username"));
        bundle.putString("password",intent.getStringExtra("password"));
        bundle.putString("name",intent.getStringExtra("name"));
        bundle.putString("email",intent.getStringExtra("email"));
        bundle.putString("phoneNo",intent.getStringExtra("phoneNo"));

        db=FirebaseFirestore.getInstance();
        db.collection("Seller").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>(){
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots){
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list){
                                ProductList temp=null;
                                //store as strings or array
                                Log.d("buyerRead",d.getId()+"=>"+d.getData());
                                String category=d.get("category").toString();
                                String description=d.get("description").toString();
                                String product=d.get("product").toString();
                                String quantity=d.get("quantity").toString();
                                String username=d.get("username").toString();
                                String cost=d.get("cost").toString();
                                ArrayList<String> image= (ArrayList<String>) d.get("image");
                                //store strings or array into the class
                                temp=new ProductList(username,category,product,Integer.parseInt(quantity),Double.parseDouble(cost),description,image);
                                //store a class into arrayList of class
                                productListArrayList.add(temp);
                            }
                        }
                    }
                });

//        fireStoreBundle.putParcelableArrayList("FireStoreProductList", (ArrayList<? extends Parcelable>) productListArrayList);
//        combinedBundle.putBundle("BuyerProfile",bundle);
//        combinedBundle.putBundle("SellerProduct",fireStoreBundle);

        animatedBottomBar = findViewById(R.id.animatedBuyerBottomBar);
        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.home_buyer, true);
            fragmentManager = getSupportFragmentManager();
            BuyerHomeFragment homeBuyerFragment = new BuyerHomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_buyer_container, homeBuyerFragment).commit();
        }
        animatedBottomBar.setOnTabSelectListener((lastIndex, lastTab, newIndex, newTab) -> {
            Fragment fragment = null;
            switch (newTab.getId()) {
                case R.id.home_buyer:
                    fragment = new BuyerHomeFragment();
                    break;
                case R.id.product_buyer:
                    fragment = new BuyerProductFragment();
                    break;
                case R.id.shop_buyer:
                    fragment = new BuyerShopFragment();
//                    fragment.setArguments(combinedBundle);
                    break;
                case R.id.me_buyer:
                    fragment = new BuyerMeFragment();
                    fragment.setArguments(bundle);
                    break;
            }
            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_buyer_container, fragment).commit();
            }
            else Log.e(TAG, "Error in creating Fragment");
        });
    }

    public class ProductList{
        private String username,category,description,product;
        private ArrayList<String>image;
        private int quantity;
        private double cost;
        public ProductList(String username,String category
                ,String product,int quantity,double cost,String description,ArrayList<String> image){
            this.username=username;
            this.category=category;
            this.product=product;
            this.quantity=quantity;
            this.description=description;
            this.image=image;
            this.cost=cost;
        }

        public ArrayList<String> getImage() {
            return image;
        }

        public String getProduct() {
            return product;
        }
    }
}