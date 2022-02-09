package com.example.mad_project.activities;

public class ShopHelperClass {
    int image;
    String name, price, state;

    public ShopHelperClass(int image, String name, String price, String state) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.state = state;
    }

    public int getImage() {
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
