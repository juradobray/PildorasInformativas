package com.parcial.brayan.appfinal;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Brayan on 21/05/2017.
 */

public class MiFirebaseMessagingService extends FirebaseMessagingService {
    String TAG="SERVICIOMENSAJE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String titulo_notificacion =remoteMessage.getNotification().getTitle();
        String contenido_notificacion=remoteMessage.getNotification()
                .getBody();
        Log.d(TAG,"Notificacion: "+titulo_notificacion+"\n"+contenido_notificacion);

    }
}
