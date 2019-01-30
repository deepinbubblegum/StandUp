package com.deepin.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static com.deepin.standup.MainActivity.NOTIFICATION_ID;
import static com.deepin.standup.MainActivity.PRIMARY_CHANNEL_ID;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }

    private void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle("Stand Up Alert")
                .setContentText("You should stand up and walk around now!")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
