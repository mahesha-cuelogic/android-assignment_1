package com.example.maheshauti.myassign2demo1.Interfaces;

import com.example.maheshauti.myassign2demo1.Models.Product;

import java.util.ArrayList;

public interface IProductList {

     void displayProductList(ArrayList<Product> response);
     void displayFailureStatus(String response);


}
