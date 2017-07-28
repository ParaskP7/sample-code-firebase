package com.hubrickchallenge.android.actions;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.util.GeneralUtil;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import static com.hubrickchallenge.android.notification.NotificationBroadcastReceiver.INTENT_ACTION_NOTIFICATION_CLEARED;
import static com.hubrickchallenge.android.notification.NotificationBroadcastReceiver.INTENT_ACTION_NOTIFICATION_CLICKED;
import static com.hubrickchallenge.android.notification.NotificationBroadcastReceiver.INTENT_EXTRA_NOTIFICATION_ID;

public class AppNotificationActions implements NotificationActions {

    private static final int DEFAULT_NOTIFICATION_ID = 0;
    private static final String DEFAULT_NOTIFICATION_CHANNEL_ID = "default_channel_id";
    private static final long[] DEFAULT_NOTIFICATION_CHANNEL_VIBRATION_PATTERN = new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400};

    private final App application;
    private final String defaultNotificationTitleText;
    private final String defaultNotificationContentText;

    public AppNotificationActions(App application) {
        this.application = application;
        this.defaultNotificationTitleText = application.getString(R.string.app_name);
        this.defaultNotificationContentText = application.getString(R.string.notification_text);
        initDefaultNotificationChannel();
    }

    private void initDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            String defaultChannelName = application.getString(R.string.notification_default_channel_name);
            NotificationChannel defaultNotificationChannel = new NotificationChannel(DEFAULT_NOTIFICATION_CHANNEL_ID, defaultChannelName,
                    NotificationManager.IMPORTANCE_DEFAULT);
            String defaultChannelDescription = application.getString(R.string.notification_default_channel_description);
            defaultNotificationChannel.setDescription(defaultChannelDescription);
            defaultNotificationChannel.enableLights(true);
            defaultNotificationChannel.setLightColor(Color.RED);
            defaultNotificationChannel.enableVibration(true);
            defaultNotificationChannel.setVibrationPattern(DEFAULT_NOTIFICATION_CHANNEL_VIBRATION_PATTERN);
            NotificationManager notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(defaultNotificationChannel);
        }
    }

    @Override
    public void show() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(application);
        notificationManagerCompat.notify(DEFAULT_NOTIFICATION_ID,
                getDefaultNotification(getContentPendingIntent(DEFAULT_NOTIFICATION_ID), getDeletedPendingIntent(DEFAULT_NOTIFICATION_ID),
                        Collections.emptyList()));
    }

    @Nullable
    PendingIntent getContentPendingIntent(int notificationId) {
        return getPendingIntent(INTENT_ACTION_NOTIFICATION_CLICKED, notificationId);
    }

    @Nullable
    PendingIntent getDeletedPendingIntent(int notificationId) {
        return getPendingIntent(INTENT_ACTION_NOTIFICATION_CLEARED, notificationId);
    }

    @Nullable
    private PendingIntent getPendingIntent(String intentAction, int notificationId) {
        Intent intent = new Intent(intentAction);
        intent.putExtra(INTENT_EXTRA_NOTIFICATION_ID, notificationId);
        int contentRequestCode = GeneralUtil.randomInt(0, Integer.MAX_VALUE - 1);
        @Nullable PendingIntent contentPendingIntent = PendingIntent.getBroadcast(application, contentRequestCode, intent,
                PendingIntent.FLAG_ONE_SHOT);
        return contentPendingIntent;
    }

    private Notification getDefaultNotification(@Nullable PendingIntent contentPendingIntent, @Nullable PendingIntent deletedPendingIntent,
                                                List<NotificationCompat.Action> notificationActions) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(application, DEFAULT_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(defaultNotificationTitleText)
                .setContentText(defaultNotificationContentText)
                .setDefaults(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_SOCIAL)
                .setContentIntent(contentPendingIntent)
                .setDeleteIntent(deletedPendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        for (NotificationCompat.Action notificationAction : notificationActions) {
            notificationBuilder.addAction(notificationAction);
        }
        return notificationBuilder.build();
    }

}
