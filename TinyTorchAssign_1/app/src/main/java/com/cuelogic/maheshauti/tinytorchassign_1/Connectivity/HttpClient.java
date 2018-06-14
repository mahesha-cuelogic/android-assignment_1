package com.cuelogic.maheshauti.tinytorchassign_1.Connectivity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cuelogic.maheshauti.tinytorchassign_1.MainActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by maheshauti on 24/03/18.
 */

public class HttpClient {
    public static final String TAG = "TAG";
    private static Response response;
    public static String getDataFromWeb( int pageNo) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://dev.tinytorch.com/api/v1/users/ae1c6696-faf7-4811-a268-d61b099de31a/posts?page="+pageNo+"&per_page=20")
                    .get()
                    .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYWUxYzY2OTYtZmFmNy00ODExLWEyNjgtZDYxYjA5OWRlMzFhIn0.tNrerFyAhtywt9NQulxYlV2-FkHj_-oFEKOGRf7fpto")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Postman-Token", "310efa05-607d-49df-8416-ce937e1d87e8")
                    .build();
            response = client.newCall(request).execute();
            System.out.println("response code is "+response.code());
            MainActivity.statuscode=response.code();
            return response.body().string();
        } catch (@NonNull IOException e) {

            Log.e(TAG, " msg for u" + e.getLocalizedMessage());


            return  null;

        }

    }

}
