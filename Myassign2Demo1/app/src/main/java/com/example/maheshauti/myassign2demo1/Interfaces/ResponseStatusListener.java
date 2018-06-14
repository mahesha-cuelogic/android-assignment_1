package com.example.maheshauti.myassign2demo1.Interfaces;

import com.example.maheshauti.myassign2demo1.Models.Product;

import java.util.ArrayList;

public interface ResponseStatusListener {
     void onSuccess(ArrayList<Product> data);
     void onFailure(int statusCode,Object failureResponse);

}
