package com.example.mad_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_project.R;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyerCart extends AppCompatActivity implements Serializable {

    Button placeOrder;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    ArrayList<ItemsInCart> itemAddedToCarts=new ArrayList<>();
    CountTotalCost countTotalCost=new CountTotalCost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buyer_cart);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        recyclerView=findViewById(R.id.cart_recycler);
//        Intent intent=getIntent();
//        itemAddedToCarts= (ArrayList<ItemsInCart>) intent.getSerializableExtra("getItems");
        placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BuyerCart.this,BuyerOrder.class);
                startActivity(intent1);
            }
        });
        GridLayoutManager layoutManager= new GridLayoutManager(this,
                2,GridLayoutManager.VERTICAL,false);
        adapter = new CheckOutAdapter(this,itemAddedToCarts);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CheckOutHolder>{
        ArrayList<ItemsInCart> itemAddedToCarts;
        Context context;
        public CheckOutAdapter(Context context,ArrayList<ItemsInCart> itemAddedToCarts){
            this.context=context;
            this.itemAddedToCarts=itemAddedToCarts;
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
        ItemsInCart item=itemAddedToCarts.get(position);
        holder.imageView.setImageDrawable(item.getImage());
        String strPrice=item.getPrice();
        String strQty=item.getQty();
        strPrice=strPrice.substring(1);
        try {
            strQty=strQty.substring(1);
            double price = Double.parseDouble(strPrice);
            double qty = Double.parseDouble(strQty);
            double sub_total = price * qty;
            double previous_total = countTotalCost.getTotal();
            countTotalCost.setTotal(sub_total + previous_total);
            holder.cartQuantity.setText(item.getQty());
            holder.cartTotalPrice.setText("" + sub_total);
            holder.cartProduct.setText(item.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        }

        @Override
        public int getItemCount() {
            return itemAddedToCarts.size();
        }

        public class CheckOutHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView cartProduct ,cartQuantity,cartTotalPrice;


            public CheckOutHolder(@NonNull View itemView) {
                super(itemView);
                imageView=findViewById(R.id.cart_image);
                cartProduct=findViewById(R.id.cart_product);
                cartQuantity=findViewById(R.id.cart_quantity);
                cartTotalPrice=findViewById(R.id.cart_totalPrice);

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


}