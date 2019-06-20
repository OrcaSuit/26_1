package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Return the communication channel to the service.
        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        String msg = data.get("msg");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        android.support.v4.app.NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "one-channel";
            String channelName = "My Channel One";
            String channelDescription = "My Channel One Description";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            manager.createNotificationChannel(channel);
            builder = new android.support.v4.app.NotificationCompat.Builder(this, channelId);
        } else {
            builder = new android.support.v4.app.NotificationCompat.Builder(this);
        }
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentText(msg);
            builder.setAutoCancel(true);

            manager.notify(222, builder.build());
        }
    }
