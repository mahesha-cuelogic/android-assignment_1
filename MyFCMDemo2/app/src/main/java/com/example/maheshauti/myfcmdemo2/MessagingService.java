package com.example.maheshauti.myfcmdemo2;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MessagingService extends FirebaseMessagingService {
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        showNotification(remoteMessage.getNotification().getBody());
//
//    }
//
//    public void showNotification(String message){
//        PendingIntent pi=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
//        Notification notification=new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("Mahi_techie")
//                .setContentText(message)
//                .setContentIntent(pi)
//                .setAutoCancel(true)
//                .build();
//
//        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notification);
//
//    }

    public static final String TAG="firebasemessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"From: "+remoteMessage.getFrom());
        //check if msg contains data
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message Data is "+remoteMessage.getData());
        }

        //check if msg contains notification

        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Message body : "+remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

    }

    private void sendNotification(String body) {
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("notifyid",101);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationsound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Mahis Firebase cloud messaging")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationsound)
                .addAction(R.mipmap.ic_launcher,"action1",pendingIntent)
                .addAction(R.mipmap.ic_launcher,"action2",pendingIntent)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificBuilder.build());



    }
}
