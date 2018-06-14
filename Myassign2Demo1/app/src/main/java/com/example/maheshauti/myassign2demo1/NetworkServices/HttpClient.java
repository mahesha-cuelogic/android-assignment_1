package com.example.maheshauti.myassign2demo1.NetworkServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.maheshauti.myassign2demo1.Activities.MainActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by maheshauti on 24/03/18.
 */
//this class request for the data from server
public class HttpClient {
    private static final String TAG = "TAG";
    private static Response response;
    public String getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://mobiletest-hackathon.herokuapp.com/getdata/")
                    .get()
                    .build();
            response = client.newCall(request).execute();

            return response.body().string();
        } catch (@NonNull IOException e) {
            Log.e(TAG, " Could not Fetch Data due to ....................." + e.getLocalizedMessage());
            return  null;

        }

    }

}
