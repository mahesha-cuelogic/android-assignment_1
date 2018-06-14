package com.androidexample.mvc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends Activity implements OnClickListener {
	
	Button btnshare, btndelete;
	String showString = "";
	int total=0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondscreen); 
		
		btnshare = (Button)findViewById(R.id.btnshare);
		btnshare.setOnClickListener(this);
		
		
    
		TextView showCartContent    = (TextView) findViewById(R.id.showCart);
		final Button thirdBtn 		= (Button) findViewById(R.id.third);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();
		
		// Get Cart Size
		final int cartSize = aController.getCart().getCartSize();
		
	
		
/******** Show Cart Products on screen - Start ********/
		
		if(cartSize >0)
		{	
			
			for(int i=0;i<cartSize;i++)
			{
				
				//Get product details
				String pName 	= aController.getCart().getProducts(i).getProductName();
				int pPrice   	= aController.getCart().getProducts(i).getProductPrice();
				String pDisc   	= aController.getCart().getProducts(i).getProductDesc();
				total+=pPrice;
				showString += "\n\nProduct Name : "+pName+"\n"+
	                               "Price : "+pPrice+"\n"+""+
	                               "\n -----------------------------------";
				
			
				
				
			}
			showString += "\n\nTotal : "+total+"\n";
		}
		else
			showString = "\n\nShopping cart is empty.\n\n";
		
		showCartContent.setText(showString);
		//Toast.makeText(getApplicationContext(),total, Toast.LENGTH_LONG).show();
		
/******** Show Cart Products on screen - End ********/
		
		thirdBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(cartSize >0)
				{
					Intent i = new Intent(getBaseContext(), ThirdScreen.class);
					startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), 
							"Shopping cart is empty.", 
							 Toast.LENGTH_SHORT).show();
			}
		});
		
	} 
	
	@Override
    protected void onDestroy() {
		
        super.onDestroy();
        
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnshare:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		   // String shareBody = "Here is the share content body";   
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Shopping list");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, showString);
		    startActivity(Intent.createChooser(sharingIntent, "Share via"));
			
			break;
		

		default:
			break;
		}
		
		
	}
}
