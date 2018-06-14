package com.example.maheshauti.myfcmdemo2;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG="FirebaseInstanceIDService";

    @Override
    public void onTokenRefresh() {

        String RefreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("in refresh token","refreshed token is"+RefreshedToken);

    }
}
