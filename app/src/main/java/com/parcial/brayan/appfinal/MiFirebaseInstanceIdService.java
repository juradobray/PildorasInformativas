package com.parcial.brayan.appfinal;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Brayan on 21/05/2017.
 */

public class MiFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG="SERVICIOTAG";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token= FirebaseInstanceId.getInstance().getToken();


        Log.d(TAG,"Token: "+token);
    }
}
