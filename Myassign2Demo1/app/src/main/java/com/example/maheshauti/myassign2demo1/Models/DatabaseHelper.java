package com.example.maheshauti.myassign2demo1.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// this class handles all database operations
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="products.db";
    private static final String PRODUCTS_TABLE_NAME=" productslist6 ";
    private static final String CART_TABLE_NAME=" newcart3 ";
    public static final String COL_1="ID";
    private static final String COL_2="PRODUCT_NAME";
    private static final String COL_3="PRICE";
    private static final String COL_4="VENDER_NAME";
    private static final String COL_5="VENDOR_ADDRESS";
    private static final String COL_6="IMAGE_URL";
    private static final String COL_7="VENDOR_PHONE_NUMBER";
    private static final String  COL_8="COUNT";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+PRODUCTS_TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME TEXT,PRICE INT,VENDER_NAME TEXT,VENDOR_ADDRESS TEXT,IMAGE_URL TEXT,VENDOR_PHONE_NUMBER LONG,COUNT INT) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CART_TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME TEXT,PRICE INT,VENDER_NAME TEXT,VENDOR_ADDRESS TEXT,IMAGE_URL TEXT,VENDOR_PHONE_NUMBER LONG,COUNT INT) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+PRODUCTS_TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String productName,String vendorName,String vendorAddress, int price,String image_url, String vendor_phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COL_2,productName);
        values.put(COL_3,price);
        values.put(COL_4,vendorName);
        values.put(COL_5,vendorAddress);
        values.put(COL_6,image_url);
        values.put(COL_7,vendor_phoneNumber);
        values.put(COL_8,1);
        long result=db.insert(PRODUCTS_TABLE_NAME,null,values);
        if(result==-1){
            return false;
        }
        return true;
    }

    public void clearTableData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("drop table if exists "+PRODUCTS_TABLE_NAME);
        onCreate(db);

    }

    public Cursor getAlldata(){
        SQLiteDatabase db=getWritableDatabase();
        try {
            Cursor res=db.rawQuery("select * from "+PRODUCTS_TABLE_NAME,null);
            return res;
        }
        catch (Exception e){
            return null;
        }

    }
public boolean addToCartTable(String productName,String vendorName,String vendorAddress, int price,String image_url, String vendor_phoneNumber){
    SQLiteDatabase db=this.getWritableDatabase();

    ContentValues values=new ContentValues();
    values.put(COL_2,productName);
    values.put(COL_3,price);
    values.put(COL_4,vendorName);
    values.put(COL_5,vendorAddress);
    values.put(COL_6,image_url);
    values.put(COL_7,vendor_phoneNumber);
        int count=-1;
    try{
        Cursor res=db.rawQuery("select "+COL_8+" from "+CART_TABLE_NAME+" where PRODUCT_NAME="+"'"+productName+"'",null);
        res.moveToFirst();
        System.out.println("count in db is "+res.getInt(0));
        count=res.getInt(0);
    }catch (Exception e){
        //when product item is not found in the table
        values.put(COL_8,1);
        db.insert(CART_TABLE_NAME,null,values);

        System.out.println(e);
        return true;
    }

    //update the row...
    ContentValues cv = new ContentValues();
    cv.put("COUNT",++count); //These Fields should be your String values of actual column names

    db.update(CART_TABLE_NAME, cv, "PRODUCT_NAME="+"'"+productName+"'", null);
    return true;
}
    public Cursor getAllCartdata(){
        SQLiteDatabase db=getWritableDatabase();
        try {
            Cursor res=db.rawQuery("select * from "+CART_TABLE_NAME,null);
            return res;
        }
        catch (Exception e){
            return null;
        }

    }
    public void removeProductFromCart(String productName){

        SQLiteDatabase db=getWritableDatabase();

       db.delete(CART_TABLE_NAME, COL_2 +"='"+productName+"'", null);



    }


}
