package com.androidexample.mvc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
;

public class CombineActivity extends Activity implements OnClickListener {
	ImageView imguchumi,imgnaivas,imgtuskys,imgnakumatt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_combine);
	     //initialization of images values
	     imguchumi = (ImageView) findViewById(R.id.imageuchu);
	     imguchumi.setOnClickListener(this);
	     imgnaivas = (ImageView) findViewById(R.id.Imagenai);
	     imgnaivas.setOnClickListener(this);
	     imgtuskys = (ImageView) findViewById(R.id.imagetuskys);
	     imgtuskys.setOnClickListener(this);
	     imgnakumatt = (ImageView) findViewById(R.id.imagenakumatt);
	     imgnakumatt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageuchu :
			Toast.makeText(getApplicationContext(), "welcome to Uchumi", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(),UchumiActivity.class));
			
			break;
		case R.id.Imagenai:
			Toast.makeText(getApplicationContext(), "welcome to Naivas", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(),NaivasActivity.class));
					
			break;
		case R.id.imagetuskys:
			Toast.makeText(getApplicationContext(), "welcome to Tuskys", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(),TuskysActivity.class));
			
			break;
		case R.id.imagenakumatt:
			Toast.makeText(getApplicationContext(), "welcome to Nakumatt", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getApplicationContext(),NakumattActivity.class));
			
			break;

		default:
			break;
		}
	}
}