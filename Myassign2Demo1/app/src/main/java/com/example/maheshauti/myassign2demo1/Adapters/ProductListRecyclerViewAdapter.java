package com.example.maheshauti.myassign2demo1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.maheshauti.myassign2demo1.Models.DatabaseHelper;
import com.example.maheshauti.myassign2demo1.Models.Product;
import com.example.maheshauti.myassign2demo1.R;

import java.util.ArrayList;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.MyViewHolder> {

     private Context context;
    private ArrayList<Product> mData;





    public ProductListRecyclerViewAdapter(Context context,ArrayList<Product> data){
        this.context=context;
        this.mData=data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(context);
        view=mInflater.inflate(R.layout.product_list_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.bindData(mData.get(position),context);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt_ProductName;
        TextView txt_Price;
        TextView txt_VenderName;
        TextView txt_Address;
        Button btn_addToCart;

        Product product;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.product_img);
            txt_ProductName=(TextView) itemView.findViewById(R.id.txt_product_name);
            txt_VenderName=(TextView) itemView.findViewById(R.id.txt_Productlist_vendor_name);
            txt_Price=(TextView) itemView.findViewById(R.id.txt_product_price);
            txt_Address=(TextView) itemView.findViewById(R.id.txt_vendor_address);
            btn_addToCart=(Button)itemView.findViewById(R.id.btn_addToCart);

        }

        public void bindData(final Product product, final Context context) {
            this.product = product;

            this.txt_ProductName.setText("Product Name : "+this.product.getProductName());

            this.txt_VenderName.setText("Vendor Name : "+this.product.getVendorName());
            this.txt_Address.setText("Vendor Address : "+this.product.getVendorAddress());

            this.txt_Price.setText("Price: "+this.product.getPrice()+"");


            Glide.with( context)
                    .load(this.product.getProductImgUrl())
                    .into(this.imageView);


            this.btn_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelper databaseHelper=new DatabaseHelper(context);
                    boolean isInserted=databaseHelper.addToCartTable(product.getProductName(),product.getVendorName(),product.getVendorAddress(),product.getPrice(),product.getProductImgUrl(),product.getPhoneNumber()+"");
                    Toast.makeText(context,product.getProductName()+" added to cart ",Toast.LENGTH_SHORT).show();
                   // MyViewHolder.this.notifyAll();

                }
            });

        }
    }
}
