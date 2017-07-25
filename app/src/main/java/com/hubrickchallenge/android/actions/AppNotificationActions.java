package com.hubrickchallenge.android.actions;

import android.app.Notification;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class AppNotificationActions implements NotificationActions {

    private final App application;
    private final String defaultNotificationTitleText;
    private final String defaultNotificationContentText;

    public AppNotificationActions(App application) {
        this.application = application;
        this.defaultNotificationTitleText = application.getString(R.string.app_name);
        this.defaultNotificationContentText = application.getString(R.string.notification_text);
    }

    @Override
    public void show() {
        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(application);
        notificationManagerCompat.notify(0, getSingleNotification(null, null, Collections.<NotificationCompat.Action>emptyList()));
    }

    private Notification getSingleNotification(@Nullable PendingIntent contentPendingIntent, @Nullable PendingIntent deletedPendingIntent,
                                               final List<NotificationCompat.Action> notificationActions) {
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(application)
                .setContentTitle(defaultNotificationTitleText)
                .setContentText(defaultNotificationContentText)
                .setDefaults(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_SOCIAL)
                .setContentIntent(contentPendingIntent)
                .setDeleteIntent(deletedPendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        for (final NotificationCompat.Action notificationAction : notificationActions) {
            notificationBuilder.addAction(notificationAction);
        }
        return notificationBuilder.build();
    }

}
