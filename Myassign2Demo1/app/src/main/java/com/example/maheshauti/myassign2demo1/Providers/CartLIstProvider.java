package com.example.maheshauti.myassign2demo1.Providers;

import android.content.Context;
import android.database.Cursor;

import com.example.maheshauti.myassign2demo1.Interfaces.IProductList;
import com.example.maheshauti.myassign2demo1.Models.DatabaseHelper;
import com.example.maheshauti.myassign2demo1.Models.Product;

import java.util.ArrayList;
// this class sends requests to database helper to do database operations
public class CartLIstProvider {
    Context mContex;
    IProductList iProductList;
    ArrayList<Product> data;
    public CartLIstProvider(IProductList iProductList, Context context){
        this.iProductList=iProductList;
        this.mContex=context;

    }
    public void getProductList() {
        DatabaseHelper mydb=new DatabaseHelper(mContex);
        Cursor res=mydb.getAllCartdata();
        StringBuffer buffer=new StringBuffer();
        data=new ArrayList<>();
        if(res!=null){
            while (res.moveToNext()){
                Product product=new Product();
                product.setProductName(res.getString(1));
                // buffer.append(res.getString(2));
                product.setPrice(res.getInt(2));
                product.setVendorName(res.getString(3));
                product.setVendorAddress(res.getString(4));
                product.setProductImgUrl(res.getString(5));
                product.setPhoneNumber(res.getLong(6));
                product.setCount(res.getInt(7));
                //System.out.println("in database count is "+res.getInt(7));
                data.add(product);
            }
            if(data.size()!=0)
            iProductList.displayProductList(data);
            else
                iProductList.displayFailureStatus("could not fetch data from database");
        }else
            iProductList.displayFailureStatus("could not fetch data from database");

    }

    public int getTotalPrice() {
        int sum=0;
        for(int i=0;i<data.size();i++){
            sum=sum+data.get(i).getPrice()*data.get(i).getCount();
        }

        return sum;
    }
}
