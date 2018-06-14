package com.example.maheshauti.sampledatabaseapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="products.db";
    public static final String TABLE_NAME="productslist1 ";
    public static final String COL_1="ID";
    public static final String COL_2="PRODUCT_NAME";
    public static final String COL_3="PRICE";
    public static final String COL_4="VENDER_NAME";
    public static final String COL_5="VENDOR_ADDRESS";
    public static final String COL_6="IMAGE_URL";
    public static final String COL_7="VENDOR_PHONE_NUMBER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME TEXT,PRICE INT,VENDER_NAME TEXT,VENDOR_ADDRESS TEXT,IMAGE_URL TEXT,VENDOR_PHONE_NUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String productname,String vendorName,String vendorAddress, int price,String image_url, String vendor_phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2,productname);
        values.put(COL_3,price);
        values.put(COL_4,vendorName);
        values.put(COL_5,vendorAddress);
        values.put(COL_6,image_url);
        values.put(COL_7,vendor_phoneNumber);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1){
            return false;
        }
        return true;
    }
    public Cursor getAlldata(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
}
