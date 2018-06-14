package com.example.maheshauti.myfcmdemo2;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

public class MainActivity extends AppCompatActivity {
        Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        int notifyid=intent.getIntExtra("notifyid",0);
        Log.d("notify id is ",""+notifyid);
        NotificationManager mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.cancel(notifyid);

        String s=FirebaseInstanceId.getInstance().getToken();
        System.out.println("token is : "+s);
        b=(Button)findViewById(R.id.btn_show_token);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=FirebaseInstanceId.getInstance().getToken();
                Log.i(" token is ",id);
                Toast.makeText(MainActivity.this, "Token is"+id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
