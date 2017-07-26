package com.hubrickchallenge.android.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hubrickchallenge.android.activity.main.MainActivity;

import timber.log.Timber;

import static com.hubrickchallenge.android.BuildConfig.APPLICATION_ID;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    public static final String INTENT_ACTION_NOTIFICATION_CLICKED = APPLICATION_ID + ".action.NOTIFICATION_CLICKED";
    public static final String INTENT_ACTION_NOTIFICATION_CLEARED = APPLICATION_ID + ".action.NOTIFICATION_CLEARED";

    public static final String INTENT_EXTRA_NOTIFICATION_ID = "INTENT_EXTRA_NOTIFICATION_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        int notificationId = extras.getInt(INTENT_EXTRA_NOTIFICATION_ID);
        if (INTENT_ACTION_NOTIFICATION_CLICKED.equals(action)) {
            Timber.i("A notification was just clicked. [Notification ID: %s]", notificationId);
            context.startActivity(MainActivity.getStartIntentFromNotification(context));
        } else if (INTENT_ACTION_NOTIFICATION_CLEARED.equals(action)) {
            Timber.i("A notification was just cleared. [Notification ID: %s]", notificationId);
        }
    }

}
