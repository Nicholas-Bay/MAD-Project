package com.example.mad_project.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="local.db";
    private static final int SCHEMA_VERSION=1;

    public LocalDataBase(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE productInCart( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " productName TEXT,productPrice TEXT, productQty TEXT, productState TEXT,productImage BLOB)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    public Cursor getAll(){
        return (getReadableDatabase().rawQuery(
                "SELECT * FROM productInCart ORDER BY productName",null));
    }
    public Cursor getbyId(String id){
        String[] args={id};
        return (getReadableDatabase().rawQuery(
                "SELECT * FROM productInCart WHERE _ID=?",args));
    }
    public void insert(String productName,String productPrice,
                       String productQty,String productState,byte[] productImage){
        ContentValues cv= new ContentValues();
        cv.put("productName",productName);
        cv.put("productPrice",productPrice);
        cv.put("productQty",productQty);
        cv.put("productState",productState);
        cv.put("productImage",productImage);
        getWritableDatabase().insert("productInCart","productName",cv);
    }
    public void update(String id,String productName,String productPrice,
                       String productQty,String productState,byte[] productImage){
        ContentValues cv= new ContentValues();
        String[] args={id};
        cv.put("productName",productName);
        cv.put("productPrice",productPrice);
        cv.put("productQty",productQty);
        cv.put("productState",productState);
        cv.put("productImage",productImage);
        getWritableDatabase().update("productInCart",cv,"_ID=?",args);
    }
    public void reset(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DROP TABLE productInCart");
        db.execSQL("CREATE TABLE productInCart( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " productName TEXT,productPrice TEXT, productQty TEXT, productState TEXT,productImage BLOB);");
    }
    public String getID(Cursor c) {
        return (c.getString(0));
    }
    public String getproductName(Cursor c){
        return (c.getString(1));
    }
    public String getproductPrice(Cursor c){
        return (c.getString(2));
    }
    public String getproductQty(Cursor c){
        return (c.getString(3));
    }
    public String getproductState(Cursor c){
        return (c.getString(4));
    }
    public byte[] getproductImage(Cursor cursor){
        return (cursor.getBlob(5));
}

}
