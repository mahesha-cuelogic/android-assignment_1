package com.example.maheshauti.myassign2demo1.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.maheshauti.myassign2demo1.NetworkServices.HttpClient;
import com.example.maheshauti.myassign2demo1.NetworkServices.JsonParser;
import com.example.maheshauti.myassign2demo1.Interfaces.ResponseStatusListener;

import org.json.JSONException;

import java.util.ArrayList;

public class DataProvider {
    public static final String TAG="DataProvider";

    ResponseStatusListener responseStatusListener=null;
    String response=null;
    Context context;

    public DataProvider( Context context){
        this.context=context;

    }

    public void getProductListFromDatabase(Context context) {

        DatabaseHelper mydb=new DatabaseHelper(context);
        Cursor res=mydb.getAlldata();
        StringBuffer buffer=new StringBuffer();
        ArrayList<Product> data=new ArrayList<>();
        if(res!=null){
            while (res.moveToNext()){
                Product product=new Product();
                product.setProductName(res.getString(1));
                // buffer.append(res.getString(2));
                product.setPrice(res.getInt(2));
                product.setVendorName(res.getString(3));
                product.setVendorAddress(res.getString(4));
                product.setProductImgUrl(res.getString(5));
                product.setPhoneNumber(res.getLong(6));
                data.add(product);
            }

            //Toast.makeText(context,"result is "+buffer,Toast.LENGTH_LONG).show();
            if(data.size()!=0)
                responseStatusListener.onSuccess(data);
            else
                responseStatusListener.onFailure(0,"Could not fetch data ");

        }else {

            responseStatusListener.onFailure(0,"Could not fetch data ");

        }
    }

    public void setResponseStatusListener(ResponseStatusListener responseStatusListener) {
        this.responseStatusListener = responseStatusListener;
    }


    @SuppressLint("StaticFieldLeak")
    public  class GetDataClient extends AsyncTask{
        Context mcontext;
        public GetDataClient(Context context){
            this.mcontext=context;

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HttpClient httpClient=new HttpClient();
             response=httpClient.getDataFromWeb();

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            System.out.println("data recieved is "+response);
            if(response!=null){
                // parse data
                ArrayList<Product> Data=new ArrayList<>();
                JsonParser jsonParser=new JsonParser();

                try {
                    Data=jsonParser.parseData(response.toString());

                    System.out.println("Data successfully parsed"+Data.get(0).getProductName());

                } catch (JSONException e) {

                    e.printStackTrace();

                }

                responseStatusListener.onSuccess(Data);
                storeProductList(mcontext,Data);
            }else {
                responseStatusListener.onFailure(0,"Could not fetch data from the server");
            }
        }

        public void storeProductList(Context context,ArrayList<Product> responce) {

            DatabaseHelper mydb=new DatabaseHelper(context);

            mydb.clearTableData();
            for(int i=0;i<responce.size();i++){
                boolean isInserted=mydb.insertData(responce.get(i).getProductName(),responce.get(i).getVendorName(),responce.get(i).getVendorAddress(),responce.get(i).getPrice(),responce.get(i).getProductImgUrl(),responce.get(i).getPhoneNumber()+"");
               // Toast.makeText(context,"data storing : "+isInserted,Toast.LENGTH_LONG).show();
            }

        }
    }
}
