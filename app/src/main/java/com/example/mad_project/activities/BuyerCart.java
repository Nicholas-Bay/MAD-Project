package com.example.mad_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.R;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyerCart extends AppCompatActivity{
    //recycler view
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    //database & arraylist
    LocalDataBase localDataBaseHelper=null;
    Cursor cursor=null;
    ArrayList<ItemsInCart> itemAddedToCarts;
    //buttons and textview
    Button placeOrder;
    TextView totalPrice;
    //count total price and display on text view
    CountTotalCost countTotalCost=new CountTotalCost();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_cart);
        getSupportActionBar().hide();

        //localdatabase
        localDataBaseHelper=new LocalDataBase(this);
        cursor=localDataBaseHelper.getAll();
        itemAddedToCarts=new ArrayList<>();
        for (;cursor.getCount()!=0&&cursor.moveToNext();) {
            ItemsInCart temp=new ItemsInCart(cursor.getBlob(5),
                    cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4));
            itemAddedToCarts.add(temp);
            Toast.makeText(this,temp.getName(),Toast.LENGTH_SHORT).show();
            Toast.makeText(this,temp.getPrice(),Toast.LENGTH_SHORT).show();
        }
        //display totalPrice
        totalPrice=findViewById(R.id.total_price);
        totalPrice.setText("Total price:"+0);
        //button for ordering
        placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localDataBaseHelper.reset();
                localDataBaseHelper.close();
                Intent intent1 = new Intent(BuyerCart.this,BuyerOrder.class);
                startActivity(intent1);
            }
        });

        //recyclerview
        recyclerView=findViewById(R.id.cart_recycler);
        GridLayoutManager layoutManager= new GridLayoutManager(this,
                2,GridLayoutManager.VERTICAL,false);
        adapter = new CheckOutAdapter(this,itemAddedToCarts);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CheckOutHolder>{
        ArrayList<ItemsInCart> totalItems;
        Context context;
        public CheckOutAdapter(Context context,ArrayList<ItemsInCart> itemsInCarts){
            this.context=context;
            this.totalItems=itemsInCarts;
        }

        @NonNull
        @Override
        public CheckOutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_design,parent,false);
            CheckOutHolder checkOutHolder=new CheckOutHolder(view);
            return checkOutHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CheckOutHolder holder, int position) {
            ItemsInCart item=totalItems.get(position);
            holder.cartProduct.setText(item.getName());
            holder.cartQuantity.setText("Quantity: "+item.getQty());
            //convert bytearray to imageview
            Drawable image =null;
            try {
            image = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length));
            holder.imageView.setImageDrawable(image);
            }
            catch (Exception e){
                e.printStackTrace();
                holder.imageView.setImageResource(R.drawable.empty);
                Toast.makeText(context,"size too large",Toast.LENGTH_SHORT).show();
            }

            //count total price where totalprice=qty*pice
            String strPrice = item.getPrice();
            String strQty = item.getQty();
            strPrice = strPrice.substring(1);
            double price = Double.parseDouble(strPrice);
            double qty = Double.parseDouble(strQty);
            double sub_total = price * qty;
            double previous_total = countTotalCost.getTotal();
            countTotalCost.setTotal(sub_total + previous_total);
            totalPrice.setText("Total Price: "+countTotalCost.getTotal());
            holder.cartPrice.setText("Total price: " + sub_total);

        }

        @Override
        public int getItemCount() {
            return itemAddedToCarts.size();
        }

        public class CheckOutHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView cartProduct ,cartQuantity,cartPrice;


            public CheckOutHolder(@NonNull View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.cart_image);
                cartProduct=itemView.findViewById(R.id.cart_product);
                cartQuantity=itemView.findViewById(R.id.cart_quantity);
                cartPrice=itemView.findViewById(R.id.cart_totalPrice);
            }
        }

    }
        public class CountTotalCost{
           private double total=0;

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }
        }

        //function to return to previous activity
        @Override
        public void onBackPressed(){
        this.finish();
        }

}