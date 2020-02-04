package com.unirail;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class SplashActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        subway.initialize();                                                                        //+beta 1.2 build 0127
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)                                           //+beta 1.2 build 0127
        {                                                                                           //+beta 1.2 build 0127
            create_channel();                                                                       //+beta 1.2 build 0127
        }                                                                                           //+beta 1.2 build 0127

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private final void create_channel()                                                             //+beta 1.2 build 0127
    {                                                                                               //+beta 1.2 build 0127
        final NotificationChannel channel_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station=new NotificationChannel("notifications_for_approaching_trains_that_does_stop_at_arrival_station","Notifications for approaching trains that does stop at arrival station",NotificationManager.IMPORTANCE_HIGH);
        final NotificationChannel channel_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station=new NotificationChannel("notifications_for_approaching_trains_that_does_not_stop_at_arrival_station","Notifications for approaching trains that does not stop at arrival station",NotificationManager.IMPORTANCE_HIGH);

        AudioAttributes.Builder builder=new AudioAttributes.Builder();
        builder.setUsage(AudioAttributes.USAGE_NOTIFICATION);
        builder.setContentType(AudioAttributes.CONTENT_TYPE_SPEECH);
        channel_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setSound
                (
                        Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.notify_approaching_train_does_stop_at_arrival_station),
                        builder.build()
                );
        channel_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setSound
                (
                        Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.notify_approaching_train_does_not_stop_at_arrival_station),
                        builder.build()
                );

        final NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(new NotificationChannel("important_notifications","Important notifications",NotificationManager.IMPORTANCE_HIGH));
        manager.createNotificationChannel(new NotificationChannel("unimportant_notifications","Unimportant notifications",NotificationManager.IMPORTANCE_LOW));
        manager.createNotificationChannel(channel_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station);
        manager.createNotificationChannel(channel_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station);
    }                                                                                               //+beta 1.2 build 0127
}