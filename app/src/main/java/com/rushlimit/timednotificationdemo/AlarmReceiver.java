package com.rushlimit.timednotificationdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Arman on 1/7/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent myintent = new Intent(context, AlarmReceiver.class);
            myintent.putExtra("RICHARD", "Notif");
            myintent.putExtra("GROUP", "SURVEY");
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), myintent, 0);
            // set for x time from shared preferences later
            alarmMgr.set(AlarmManager.RTC, Long.parseLong(context.getSharedPreferences("MAIN", Context.MODE_PRIVATE).getString("TIME", "0")), alarmIntent);
        } else {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle("Survey Available")
                    .setContentText(intent.getStringExtra("RICHARD"))
                    .setSmallIcon(R.drawable.abc_ic_go_search_api_mtrl_alpha)
                    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0))
                    .setAutoCancel(true)
                    .setGroup(intent.getStringExtra("SURVEY"))
                    .build();

            notificationManager.notify(0, notification);
        }
    }
}
