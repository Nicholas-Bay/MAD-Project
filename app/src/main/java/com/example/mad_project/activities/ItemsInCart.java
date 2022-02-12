package com.example.mad_project.activities;

public class ItemsInCart {
    private byte[] image;
    private String name, price, state;
    private String Qty;

    public ItemsInCart(byte[]image, String name, String price, String QtyAdded, String state) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.state = state;
        this.Qty = QtyAdded;
    }

    public byte[] getImage() {
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

}
