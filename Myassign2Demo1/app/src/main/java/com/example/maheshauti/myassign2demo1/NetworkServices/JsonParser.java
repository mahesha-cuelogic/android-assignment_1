package com.example.maheshauti.myassign2demo1.NetworkServices;

import com.example.maheshauti.myassign2demo1.Models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//this class contains method which parses the JSON string and returns the Arraylist<Product>
public class JsonParser {
    public ArrayList<Product> parseData(String dataString) throws JSONException {
        ArrayList<Product> Data=new ArrayList<>();

        JSONObject response=new JSONObject(dataString);
        JSONArray productsArray=response.getJSONArray("products");
        for(int i=0;i<productsArray.length();i++){
            JSONObject productitem=productsArray.getJSONObject(i);
            Product product=new Product();
            product.setProductName(productitem.getString("productname"));
            product.setVendorName(productitem.getString("vendorname"));
            product.setProductImgUrl(productitem.getString("productImg"));
            product.setPrice(productitem.getInt("price"));
            product.setVendorAddress(productitem.getString("vendoraddress"));
            product.setPhoneNumber(productitem.getLong("phoneNumber"));
            Data.add(product);

        }

        return Data;
    }
}
