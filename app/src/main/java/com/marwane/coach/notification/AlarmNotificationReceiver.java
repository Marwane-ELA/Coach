package com.marwane.coach.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.marwane.coach.R;

/**
 * bronnen:
 * Use of BroadcastReceiver
 * https://developer.android.com/reference/android/content/BroadcastReceiver
 * https://developer.android.com/training/notify-user/build-notification
 * https://www.youtube.com/watch?v=hD0rybdMtGk
 * https://www.youtube.com/watch?v=SWsuijO5NGE
 * https://www.youtube.com/watch?v=CVI4CfdtbkA
 *

 */
public class AlarmNotificationReceiver extends BroadcastReceiver {
    //Create local notification when alarm wake up
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(context.getString(R.string.notifTitle))
                .setContentText(context.getString(R.string.notifText))
                .setContentInfo("Info");

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


    }
}
