package com.example.maheshauti.broadcastreciverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("intent recieved");
        Toast.makeText(context,"massage recieved",Toast.LENGTH_SHORT).show();
    }
}
