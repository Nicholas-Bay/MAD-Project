package com.example.mad_project.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ProductAddHelper {
    Drawable drawable;
    Bitmap bitmap;
    int position=0;

    public ProductAddHelper(Drawable drawable) {
        this.drawable=drawable;
    }
    public ProductAddHelper() {
    }

    public Drawable getDrawable() {
        return drawable;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap=bitmap;
    }
    public void setDrawable(Drawable drawable){
        this.drawable=drawable;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position=position;
    }
}
