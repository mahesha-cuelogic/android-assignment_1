package com.example.maheshauti.myassign2demo1.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maheshauti.myassign2demo1.Adapters.CartListRecyclerViewAdapter;
import com.example.maheshauti.myassign2demo1.Interfaces.IProductList;
import com.example.maheshauti.myassign2demo1.Interfaces.CartListChangeListener;
import com.example.maheshauti.myassign2demo1.Models.Product;
import com.example.maheshauti.myassign2demo1.Providers.CartLIstProvider;
import com.example.maheshauti.myassign2demo1.R;

import java.util.ArrayList;

public class CartListFragment extends Fragment implements IProductList,CartListChangeListener {
    public static final String TAG="CartListFragment";
    CartLIstProvider listProvider;
    RecyclerView cartListRecyclerview;
    CartListRecyclerViewAdapter adapter;
    ImageView emptyCartImage;
    SwipeRefreshLayout swipeRefresh_CartList;
    TextView totalPriceView;

    public static CartListFragment newInstance() {
        return new CartListFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.cart_list_fragement, container, false);
        initComponents(layout);

        swipeRefresh_CartList.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                        listProvider.getProductList();
                        int price = listProvider.getTotalPrice();
                        totalPriceView.setText("Total Price : "+price);
                    }
                }
        );
        listProvider.getProductList();
        int price = listProvider.getTotalPrice();
        totalPriceView.setText("Total Price : "+price);
        return layout;

    }


    private void initComponents(View layout) {
        listProvider=new CartLIstProvider(this,this.getContext());
        cartListRecyclerview = layout.findViewById(R.id.recycler_view_cartproductlist);
        emptyCartImage=layout.findViewById(R.id.image_empty_cart);
        swipeRefresh_CartList=layout.findViewById(R.id.swiperefresh);
        totalPriceView=layout.findViewById(R.id.totalPriceView);

    }

    @Override
    public void displayProductList(ArrayList<Product> response) {
        adapter = new CartListRecyclerViewAdapter(getContext(), response,this);
        cartListRecyclerview.setAdapter(adapter);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        animator.setRemoveDuration(500);
        cartListRecyclerview.setItemAnimator(animator);
        cartListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()) );
        swipeRefresh_CartList.setRefreshing(false);
        emptyCartImage.setVisibility(View.GONE);
    }

    @Override
    public void displayFailureStatus(String response) {
        emptyCartImage.setVisibility(View.VISIBLE);
        swipeRefresh_CartList.setRefreshing(false);
    }

    @Override
    public void updateprice() {
        int price = listProvider.getTotalPrice();
        totalPriceView.setText("Total Price :"+price);

    }

    @Override
    public void showEmptyList() {
        emptyCartImage.setVisibility(View.VISIBLE);
    }
}
