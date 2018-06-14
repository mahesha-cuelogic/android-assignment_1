package com.example.maheshauti.myassign2demo1.Activities;



import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maheshauti.myassign2demo1.Adapters.ProductListRecyclerViewAdapter;
import com.example.maheshauti.myassign2demo1.Interfaces.IProductList;
import com.example.maheshauti.myassign2demo1.Models.Product;
import com.example.maheshauti.myassign2demo1.Providers.ProductListProvider;
import com.example.maheshauti.myassign2demo1.R;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;


public class ProductListFragment extends Fragment implements IProductList
{
    private final static String TAG="PRODUCT LIST FRAGMENT";
    ProductListProvider listProvider;
    RecyclerView productListRecyclerview;
    ProgressBar progressBar;
    ProductListRecyclerViewAdapter adapter;
    ImageView errorImageView;
    SwipeRefreshLayout swipeRefresh_ProductList;
    MenuView.ItemView destview;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"ONCREATE");
        View layout = inflater.inflate(R.layout.product_list_fragment, container, false);
        initComponents(layout);
        listProvider.getProductList();
        return layout;
    }
    //initilizing the components
    private  void initComponents(View layout){

        listProvider=new ProductListProvider(this,this.getContext());
        productListRecyclerview = layout.findViewById(R.id.recycler_view_productlist);
        progressBar = layout.findViewById(R.id.progressBar1);
        errorImageView=layout.findViewById(R.id.errorImage);
        swipeRefresh_ProductList=layout.findViewById(R.id.swiperefresh_productlist);
        destview=layout.findViewById(R.id.action_item2);

        swipeRefresh_ProductList.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                        listProvider.getProductList();
                    }
                }
        );

    }
    //method to create a new instance
    public static ProductListFragment newInstance() {

        return new ProductListFragment();
    }

    //implimented methods of Iproductlist
    @Override
    public void displayProductList(ArrayList<Product> response) {
        Log.d(TAG,"in display prouduct list... response is "+response.toString());

        System.out.println("in display productlist recycler view is "+productListRecyclerview);

        adapter = new ProductListRecyclerViewAdapter(getContext(), response);
        productListRecyclerview.setAdapter(adapter);
        productListRecyclerview.setItemAnimator(new ScaleInAnimator());
        Activity activity = getActivity();
        try
        {
            productListRecyclerview.setLayoutManager(new GridLayoutManager(this.getActivity(),calculateNumberOfColumns(2)));

        }
        catch (Exception e){

        }
        progressBar.setVisibility(View.GONE);
        swipeRefresh_ProductList.setRefreshing(false);
        errorImageView.setVisibility(View.GONE);
    }

    @Override
    public void displayFailureStatus(String response) {

        Log.d(TAG,"in displayFailureStatus... response is "+response.toString());
        Toast.makeText(getContext(),"Failed :"+response,Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        errorImageView.setVisibility(View.VISIBLE);

    }

    protected int calculateNumberOfColumns(int base){
        int columns = base;
        String screenSize = getScreenSizeCategory();

        if(screenSize.equals("small")){
            if(base!=1){
                columns = columns-1;
            }
        }else if (screenSize.equals("normal")){
            // Do nothing
        }else if(screenSize.equals("large")){
            columns += 2;
        }else if (screenSize.equals("xlarge")){
            columns += 3;
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            columns = (int) (columns * 1.5);
        }

        return columns;
    }

    protected String getScreenSizeCategory(){
        int screenLayout = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenLayout){
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                // small screens are at least 426dp x 320dp
                System.out.println("screensize is small");
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                // normal screens are at least 470dp x 320dp
                System.out.println("screensize is normal");
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                // large screens are at least 640dp x 480dp
                System.out.println("screensize is larg");
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                // xlarge screens are at least 960dp x 720dp
                System.out.println("screensize is xlarge");
                return "xlarge";
            default:
                return "undefined";
        }
    }



}
