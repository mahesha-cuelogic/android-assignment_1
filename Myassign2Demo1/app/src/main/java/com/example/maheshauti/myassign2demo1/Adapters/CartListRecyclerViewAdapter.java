package com.example.maheshauti.myassign2demo1.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maheshauti.myassign2demo1.Interfaces.CartListChangeListener;
import com.example.maheshauti.myassign2demo1.Models.DatabaseHelper;
import com.example.maheshauti.myassign2demo1.Models.Product;
import com.example.maheshauti.myassign2demo1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartListRecyclerViewAdapter extends RecyclerView.Adapter<CartListRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Product> mData;
    private CartListChangeListener cartListChangeListener;


    public CartListRecyclerViewAdapter(Context context, ArrayList<Product> data,CartListChangeListener cartListChangeListener) {
        this.context = context;
        this.mData = data;
        this.cartListChangeListener = cartListChangeListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cart_list_card, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        System.out.println("onbindviewholder");

        holder.binddata(mData.get(position));
        final Product product=mData.get(position);
        holder.removeFromcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper mydb=new DatabaseHelper(context);
                int count=product.getCount();
                if(count==1){
                    mydb.removeProductFromCart(product.getProductName());
                    System.out.println(product.getPhoneNumber()+" deleted");
                    Toast.makeText(context,product.getProductName()+" deleted",Toast.LENGTH_LONG).show();
                    mData.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mData.size());
                    cartListChangeListener.updateprice();
                    if(mData.size()==0){
                        cartListChangeListener.showEmptyList();
                    }
                }
                else
                {
                    product.setCount(--count);
                    cartListChangeListener.updateprice();
                    notifyDataSetChanged();

                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_productname;
        TextView txt_vendorname;
        TextView txt_price;
        TextView txt_vendorAddress;
        TextView txt_count;
        Button callVendor;
        Button removeFromcart;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_in_cart);
            txt_productname = itemView.findViewById(R.id.txt_card_view_product_name);
            txt_price = itemView.findViewById(R.id.txt_cartview_price);
            txt_vendorAddress = itemView.findViewById(R.id.txt_card_view_Vendor_add);
            txt_vendorname = itemView.findViewById(R.id.txt_card_view_vendor_name);
            callVendor = itemView.findViewById(R.id.btn_call_vendor);
            removeFromcart = itemView.findViewById(R.id.btn_remove_from_cart);
            txt_count=itemView.findViewById(R.id.txt_cartview_count);
        }

        public void binddata(final Product product) {
            txt_productname.setText("Product Name :" + product.getProductName());
            txt_vendorname.setText("Vendor Name :" + product.getVendorName());
            txt_vendorAddress.setText("Vendor Address :" + product.getVendorAddress());
            txt_price.setText("" + product.getPrice());
            txt_count.setText("Count :"+product.getCount()+"");
            System.out.println("count is "+product.getCount());
            Glide.with(context).load(product.getProductImgUrl()).into(this.imageView);
            callVendor.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("button clicked");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+product.getPhoneNumber()));
                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("permission denied");
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{Manifest.permission.CALL_PHONE},143);
                       }

                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        context.startActivity(callIntent);

                    }
                }
            });
        }
    }

}
