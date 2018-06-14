package com.androidexample.mvc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThirdScreen extends Activity implements OnClickListener {
	
	Button btnshare;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdscreen); 
		
		btnshare =  (Button)findViewById(R.id.btnshare);
		btnshare.setOnClickListener(this);
    
		TextView showCartContent    = (TextView) findViewById(R.id.showCart);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();
        
		int cartSize = aController.getCart().getCartSize();
		
		String showString = "";
		
/******** Show Cart Products on screen - Start ********/	
		
			for(int i=0;i<cartSize;i++)
			{
				//Get product details
				String pName 	= aController.getCart().getProducts(i).getProductName();
				int pPrice   	= aController.getCart().getProducts(i).getProductPrice();
				String pDisc   	= aController.getCart().getProducts(i).getProductDesc();
				
				showString += "\n\nProduct Name : "+pName+"\n"+
	                               "Price : "+pPrice+"\n"+""+
	                               "\n -----------------------------------";
			}
		
		
		showCartContent.setText(showString);
		
/******** Show Cart Products on screen - End ********/	
		
	}

	@Override
	public void onClick(View v) {
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
	    sharingIntent.setType("text/plain");
	    String shareBody = "Here is the share content body";
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
	startActivity(Intent.createChooser(sharingIntent, "Share via"));
		
	} 
	
}
