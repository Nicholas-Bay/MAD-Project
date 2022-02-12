package com.example.mad_project.fragments;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.R;
import com.example.mad_project.activities.BuyerCart;
import com.example.mad_project.activities.ItemsInCart;
import com.example.mad_project.activities.LocalDataBase;
import com.example.mad_project.activities.ProductList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

public class BuyerShopFragment extends Fragment{
    //recyclerview
    RecyclerView shopRecycler;
    RecyclerView.Adapter adapter;
    //cart
    ImageView cartCheckout;
    //store stuff
    ArrayList<ProductList> productArrayList;// from server
    ArrayList<ShopHelperClass> shopItems; //localClass
//  firestore
    FirebaseFirestore db;
    //firebase storage
    FirebaseStorage storage;
    StorageReference storageReference;
    //
    Intent intent;
    //things need to pass to cart check out activity
    LocalDataBase localDataBaseHelper=null;
    //
    public BuyerShopFragment() {
        super(R.layout.fragment_buyer_shop);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_shop, container, false);
        intent= new Intent(getContext(), BuyerCart.class);
        //set id
        shopRecycler = view.findViewById(R.id.shop_recycler);
        cartCheckout = view.findViewById(R.id.cart_checkout);

        productArrayList=new ArrayList<>();
        shopItems = new ArrayList<>();


        localDataBaseHelper=new LocalDataBase(getContext());
        localDataBaseHelper.getWritableDatabase().execSQL("create table if not exists productInCart(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" productName TEXT,productPrice TEXT, productQty TEXT, " +
                "productState TEXT,productImage BLOB)");
        //scan for products
        dbScan();
        //running cart
        cartCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        shopRecycler();
        return view;
    }
//get data from database
    private void dbScan(){
        storage=FirebaseStorage.getInstance();
        db= FirebaseFirestore.getInstance();
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
                                productArrayList.add(temp);
                                get1ImageFBD(image,product,cost,Integer.parseInt(quantity));
                                //store in intent

                            }
                        }
                    }
                });
    }
    //getImagePathAndDownloadIt
    private void get1ImageFBD(ArrayList<String>tempImageRef,String product, String price,int quantity){
        //get Image
        if (!tempImageRef.isEmpty()) {
            String tempImage1=tempImageRef.get(0);
            StorageReference imageStorage= storage.getReference(tempImage1);
            String fileName=imageStorage.getName();
            try{
                final File localFile= File.createTempFile(fileName,"jpg");
                imageStorage.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                //idk will have crash if i use getActivity so ya
                                Toast.makeText(getActivity(),"Picture Loaded Success for "+product,Toast.LENGTH_SHORT).show();
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                if(quantity!=0)
                                shopItems.add(new ShopHelperClass(new BitmapDrawable(getResources(),bitmap),product,"$"+price,"Available"));
                                else
                                    shopItems.add(new ShopHelperClass(new BitmapDrawable(getResources(),bitmap),product,"$"+price,"UnAvailable"));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Picture Loaded Failed",Toast.LENGTH_SHORT).show();

                    }
                });

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else return;
    }

    //recycler
    private void shopRecycler(){
        shopRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        shopRecycler.setLayoutManager(layoutManager);

        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.applewatch)
                , "Apple Watch Series 7", "$500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.iphone)
                , "iPhone 13 Pro Max", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.airpods_max)
                , "Airpods Max", "$600", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.airpods_pro)
                , "Airpods Pro", "$1500", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.mbp)
                , "Macbook Pro", "$1900", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.ipad_pro)
                , "Ipad Pro", "$800", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.iphonese)
                , "Iphone SE", "$600", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.applecharger)
                , "Apple 25W charger", "$800", "Available"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.macbookairm2)
                , "Macbook Air M2", "$1200", "UnAvailable"));
        shopItems.add(new ShopHelperClass(getContext().getResources().getDrawable(R.drawable.ipadmini)
                , "Ipad mini", "$700", "Available"));


        adapter = new ShopAdapter(getContext(),shopItems);
        shopRecycler.setAdapter(adapter);

    }

    public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

        ArrayList<ShopHelperClass> shopItems;
        Context context;

        public ShopAdapter(Context context, ArrayList<ShopHelperClass> shopItems) {
            this.shopItems = shopItems;
            this.context = context;
            //hi
        }

        @NonNull
        @Override
        public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_row,parent,false);
            ShopViewHolder shopViewHolder = new ShopViewHolder(view);
            return shopViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
                ShopHelperClass shopHelperClass = shopItems.get(position);
                holder.image.setImageDrawable(shopHelperClass.getImage());
                holder.name.setText(shopHelperClass.getName());
                holder.price.setText(shopHelperClass.getPrice());
                holder.state.setText(shopHelperClass.getState());
                if (shopHelperClass.getState().equals("UnAvailable")){
                    holder.incrementQty.setActivated(false);
                    holder.decrementQty.setActivated(false);
                    holder.addToCart.setActivated(false);
                }
            holder.incrementQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp=Integer.parseInt(holder.valueQty.getText().toString());
                    temp++;
                    holder.valueQty.setText(String.valueOf(temp));
                }
            });
            holder.decrementQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp=Integer.parseInt(holder.valueQty.getText().toString());
                    temp--;
                    if(temp<=0)temp=0;
                    holder.valueQty.setText(String.valueOf(temp));
                }
            });
            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tempNameProduct = holder.name.getText().toString();
                    String tempPriceProduct = holder.price.getText().toString();
                    String tempStateProduct = holder.state.getText().toString();
                    String tempValueQty = holder.valueQty.getText().toString();
                    if (tempValueQty.equals("0")) {
                        Toast.makeText(context, "Please add more than 0 "
                                + tempNameProduct + " into your cart", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, tempNameProduct + " addedToCart", Toast.LENGTH_SHORT).show();

                        Drawable tempImageProduct = holder.image.getDrawable();
                        Bitmap temp = ((BitmapDrawable) tempImageProduct).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        temp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] tempImageByte = baos.toByteArray();

//                    itemAddtoCart.add(new ItemsInCart(tempImageByte,
//                            tempNameProduct,tempPriceProduct,tempValueQty,tempStateProduct));
                        localDataBaseHelper.insert(tempNameProduct,
                                tempPriceProduct, tempValueQty, tempStateProduct, tempImageByte);
                        holder.valueQty.setText("" + 0);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return shopItems.size();
        }

        public class ShopViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView name, price, state;
            Button decrementQty , incrementQty, addToCart;
            EditText valueQty;

            public ShopViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.shop_image);
                name = itemView.findViewById(R.id.shop_name);
                price = itemView.findViewById(R.id.shop_price);
                state = itemView.findViewById(R.id.shop_state);
                decrementQty=itemView.findViewById(R.id.decrement);
                incrementQty=itemView.findViewById(R.id.increment);
                valueQty=itemView.findViewById(R.id.value);
                addToCart=itemView.findViewById(R.id.addToCartButton);
            }
        }
    }

    public class ShopHelperClass {
        Drawable image;
        String name, price, state;
        public ShopHelperClass(Drawable image, String name, String price, String state) {
            this.image = image;
            this.name = name;
            this.price = price;
            this.state = state;
        }

        public Drawable getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getState() {
            return state;
        }
    }

}