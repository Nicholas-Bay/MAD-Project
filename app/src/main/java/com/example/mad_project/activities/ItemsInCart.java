package com.example.mad_project.activities;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ItemsInCart {
    private ArrayList<ItemsInCart> itemsInCarts;
    private Drawable image;
    private String name, price, state;
    private String Qty;

    public ItemsInCart(Drawable image, String name, String price, String QtyAdded, String state) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.state = state;
        this.Qty = QtyAdded;
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

    public String getQty() {
        return Qty;
    }

    public String getState() {
        return state;
    }

    public ArrayList<ItemsInCart> getItemsInCarts() {
        return itemsInCarts;
    }

    public void setItemsInCarts(ArrayList<ItemsInCart> itemsInCarts) {
        this.itemsInCarts = itemsInCarts;
    }
}
