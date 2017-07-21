package com.hubrickchallenge.android.actions;

import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;

public interface SnackbarActions {

    void setCoordinatorLayout(@Nullable CoordinatorLayout coordinatorLayout);

    void show(int textResId);

}
