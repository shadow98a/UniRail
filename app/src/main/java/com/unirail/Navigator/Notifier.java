package com.unirail.Navigator;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.unirail.R;
import com.unirail.UniRail;

public class Notifier
{
    static private final int id=0;

    private Context context;

    private NotificationCompat.Builder builder_for_important_notifications;
    private NotificationCompat.Builder builder_for_unimportant_notifications;
    private NotificationCompat.Builder builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station;
    private NotificationCompat.Builder builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station;

    public Notifier(final Context context)
    {
        this.context=context;

        if (Build.VERSION.SDK_INT< Build.VERSION_CODES.O)
        {
            builder_for_important_notifications=new NotificationCompat.Builder(this.context);
            builder_for_unimportant_notifications=new NotificationCompat.Builder(this.context);
            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station=new NotificationCompat.Builder(this.context);
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station=new NotificationCompat.Builder(this.context);

            builder_for_important_notifications.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setPriority(NotificationCompat.PRIORITY_HIGH);

            builder_for_important_notifications.setDefaults(NotificationCompat.DEFAULT_ALL);
            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setDefaults(NotificationCompat.DEFAULT_ALL);
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setDefaults(NotificationCompat.DEFAULT_ALL);

            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setSound(Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.notify_approaching_train_does_stop_at_arrival_station));
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setSound(Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.notify_approaching_train_does_not_stop_at_arrival_station));
        }
        else
        {
            builder_for_important_notifications=new NotificationCompat.Builder(this.context,"important_notifications");
            builder_for_unimportant_notifications=new NotificationCompat.Builder(this.context,"unimportant_notifications");
            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station=new NotificationCompat.Builder(this.context,"notifications_for_approaching_trains_that_does_stop_at_arrival_station");
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station=new NotificationCompat.Builder(this.context,"notifications_for_approaching_trains_that_does_not_stop_at_arrival_station");
        }

        builder_for_important_notifications.setSmallIcon(android.R.drawable.sym_def_app_icon);
        builder_for_unimportant_notifications.setSmallIcon(android.R.drawable.sym_def_app_icon);
        builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setSmallIcon(android.R.drawable.sym_def_app_icon);
        builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setSmallIcon(android.R.drawable.sym_def_app_icon);

        builder_for_important_notifications.setShowWhen(false);
        builder_for_unimportant_notifications.setShowWhen(false);
        builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setShowWhen(false);
        builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setShowWhen(false);

        builder_for_important_notifications.setContentTitle("UNIRAIL service");
        builder_for_unimportant_notifications.setContentTitle("UNIRAIL service");
        builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setContentTitle("UNIRAIL service");
        builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setContentTitle("UNIRAIL service");

//        builder_for_important_notifications.setOngoing(true);
//        builder_for_unimportant_notifications.setOngoing(true);
//        builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setOngoing(true);
//        builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setOngoing(true);
    }

    public final void notify_importantly(final String content_text)
    {
        if(UniRail.does_notify_importantly)
        {
            builder_for_important_notifications.setContentText(content_text);
            ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(id,builder_for_important_notifications.build());
        }
        else
        {
            notify_unimportantly(content_text);
        }
    }

    public final void notify_unimportantly(final String content_text)
    {
        builder_for_unimportant_notifications.setContentText(content_text);
        ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(id,builder_for_unimportant_notifications.build());
    }

    public final void notify_approaching_train_does_stop_at_arrival_station(final String content_text)
    {
        if(UniRail.does_notify_importantly)
        {
            builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.setContentText(content_text);
            ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(id,builder_for_notifications_for_approaching_trains_that_does_stop_at_arrival_station.build());
        }
        else
        {
            notify_unimportantly(content_text);
        }
    }

    public final void notify_approaching_train_does_not_stop_at_arrival_station(final String content_text)
    {
        if(UniRail.does_notify_importantly)
        {
            builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.setContentText(content_text);
            ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(id,builder_for_notifications_for_approaching_trains_that_does_not_stop_at_arrival_station.build());
        }
        else
        {
            notify_unimportantly(content_text);
        }
    }
}
