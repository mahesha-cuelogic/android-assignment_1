package com.example.maheshauti.myassign2demo1.Models;

import java.util.ArrayList;

public class Product {
    private String productName;
    private String vendorName;
    private String vendorAddress;
    private String productImgUrl;
    private ArrayList<String> productGallary;
    private int price;

    private long phoneNumber;

    private int count;


//    public Product(String productName, String vendorName, String vendorAddress, String productImgUrl) {
//        this.productName = productName;
//        this.vendorName = vendorName;
//        this.vendorAddress = vendorAddress;
//        this.productImgUrl = productImgUrl;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public ArrayList<String> getProductGallary() {
        return productGallary;
    }

    public void setProductGallary(ArrayList<String> productGallary) {
        this.productGallary = productGallary;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
