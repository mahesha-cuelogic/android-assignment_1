package com.example.maheshauti.myassign2demo1.Providers;


import android.content.Context;
import android.util.Log;

import com.example.maheshauti.myassign2demo1.Interfaces.IProductList;
import com.example.maheshauti.myassign2demo1.Models.DataProvider;
import com.example.maheshauti.myassign2demo1.Models.Product;
import com.example.maheshauti.myassign2demo1.NetworkServices.NetworkChecker;
import com.example.maheshauti.myassign2demo1.Interfaces.ResponseStatusListener;



import java.util.ArrayList;
//this class checks is an interface between view and dataprovider..
public class ProductListProvider {
    public static final String TAG="ProductListProvider";
    IProductList productList;
    Context context;
    NetworkChecker networkChecker;
    DataProvider dataProvider;



    public ProductListProvider(IProductList iProductList, Context context){

        dataProvider=new DataProvider(context);
        this.productList=iProductList;
        this.context=context;
    }

    public void getProductList()
    {
        networkChecker=new NetworkChecker(context);//cheaking network status
        dataProvider.setResponseStatusListener(new ResponseStatusListener() {
            @Override
            public void onSuccess(ArrayList<Product> data) {
                Log.d(TAG,"in onSuccess");
                productList.displayProductList(data);

            }
            @Override
            public void onFailure(int statusCode, Object failureResponse) {
                Log.d(TAG,"in onFailure");
                productList.displayFailureStatus(failureResponse.toString());
            }
        });


        //   dataProvider.getProductListFromWeb();
        if(networkChecker.isConnected) {
            try{
                dataProvider.new GetDataClient(context).execute();
            }
            catch (Exception e){
                System.out.println("error is "+e);
            }

        }
        else
            {
            dataProvider.getProductListFromDatabase(context);

        }

    }





}
