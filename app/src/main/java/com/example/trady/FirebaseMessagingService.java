package com.example.trady;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Toast.makeText(getApplicationContext(),"Messaging Service",Toast.LENGTH_LONG).show();
        Log.d("TAG", "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("TAG", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String msg = remoteMessage.getNotification().getBody();
            //Toast.makeText(getApplicationContext(),"Notify : `121312423435",Toast.LENGTH_LONG).show();
        }

    }
}
