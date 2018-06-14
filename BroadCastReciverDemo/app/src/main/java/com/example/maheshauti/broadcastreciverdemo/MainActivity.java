package com.example.maheshauti.broadcastreciverdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.sendBtn);

    }

    public void sendBroadcast(View view) {
        Intent intent=new Intent("hii");
        sendBroadcast(intent);
    }
}
