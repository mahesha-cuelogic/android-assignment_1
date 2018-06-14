package com.example.maheshauti.myassign2demo1.NetworkServices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
// this class checks the network connection and sets the variable isConnected true or false...
public class NetworkChecker {

    Context context;
    public  boolean isConnected;

    public NetworkChecker(Context context){
        this.context=context;
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean connectionStatus = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
       isConnected=connectionStatus;

    }



}
