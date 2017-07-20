package com.hubrickchallenge.android.actions;

import android.support.design.widget.CoordinatorLayout;

public interface SnackbarActions {

    void setCoordinatorLayout(CoordinatorLayout coordinatorLayout);

    void show(int textResId);

}
