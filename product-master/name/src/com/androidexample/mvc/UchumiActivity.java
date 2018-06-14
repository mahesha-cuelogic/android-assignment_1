package com.androidexample.mvc;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

;

public class UchumiActivity extends Activity implements OnClickListener  {
	Button btnnext;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_uchumi); btnnext = (Button) findViewById(R.id.btnnext);
	     btnnext.setOnClickListener(this);
	     
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnnext:
		startActivity(new Intent(getApplicationContext(),FirstScreen.class));
			
			
			break;

		default:
			break;
		}
		
	}
	}