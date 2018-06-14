package com.example.maheshauti.sampledatabaseapp2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
//        boolean isInserted=myDb.insertData("defaultproductname","mahesh","pune",200);
//        Toast.makeText(this,"result is "+isInserted,Toast.LENGTH_LONG).show();
        Cursor res=myDb.getAlldata();
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append(res.getString(2));
        }
        Toast.makeText(this,"result is "+buffer,Toast.LENGTH_LONG).show();

    }
}
