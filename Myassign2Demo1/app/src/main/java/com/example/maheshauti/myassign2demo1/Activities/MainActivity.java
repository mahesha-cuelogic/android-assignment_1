package com.example.maheshauti.myassign2demo1.Activities;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.maheshauti.myassign2demo1.R;

public class MainActivity extends AppCompatActivity {

    Fragment productListFragment;
    Fragment cardListFragment;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            String currentfragment=savedInstanceState.getString("currentFragment");
            if(currentfragment.equals("productListFragment")){
                productListFragment = ProductListFragment.newInstance();
                selectedFragment=productListFragment;
            }
            else
            {    cardListFragment = CartListFragment.newInstance();
                selectedFragment=cardListFragment;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linear_layout,selectedFragment);
            transaction.commit();
        }else {
            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linear_layout,ProductListFragment.newInstance());
            transaction.commit();
        }

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_item1:
                        if(productListFragment==null){
                            productListFragment = ProductListFragment.newInstance();
                            selectedFragment=productListFragment;
                          }
                        else
                        {
                            selectedFragment=productListFragment;
                        }

                        break;
                    case R.id.action_item2:
                        if(cardListFragment==null){
                            cardListFragment = CartListFragment.newInstance();
                            selectedFragment=cardListFragment;
                        }
                        else
                        {
                            selectedFragment=cardListFragment;
                        }
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(selectedFragment==productListFragment)
            outState.putString("currentFragment","productListFragment");
        else {
            outState.putString("currentFragment","cartListFragment");
        }
        super.onSaveInstanceState(outState);
    }
}
