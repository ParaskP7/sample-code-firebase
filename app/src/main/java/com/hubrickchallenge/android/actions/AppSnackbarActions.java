package com.hubrickchallenge.android.actions;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;

import javax.annotation.Nullable;

public class AppSnackbarActions implements SnackbarActions {

    private final App application;
    private final String defaultActionText;

    @Nullable
    private CoordinatorLayout coordinatorLayout;

    public AppSnackbarActions(App application) {
        this.application = application;
        this.defaultActionText = application.getString(R.string.snackbar_firebase_default_action_text);
    }

    @Override
    public void setCoordinatorLayout(@Nullable CoordinatorLayout coordinatorLayout) {
        this.coordinatorLayout = coordinatorLayout;
    }

    @Override
    public void show(int textResId) {
        final String text = application.getString(textResId);
        if (coordinatorLayout != null) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG)
                    .setAction(defaultActionText, null);
            snackbar.show();
        } else {
            Toast.makeText(application, text, Toast.LENGTH_LONG).show();
        }
    }

}
