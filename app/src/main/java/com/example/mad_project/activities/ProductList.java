package com.example.mad_project.activities;

import java.util.ArrayList;

public class ProductList {
    private String username, category, description, product;
    private ArrayList<String> image;
    private int quantity;
    private double cost;

    public ProductList(String username, String category
            , String product, int quantity, double cost, String description, ArrayList<String> image) {
        this.username = username;
        this.category = category;
        this.product = product;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.cost = cost;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public String getProduct() {
        return product;
    }
}
