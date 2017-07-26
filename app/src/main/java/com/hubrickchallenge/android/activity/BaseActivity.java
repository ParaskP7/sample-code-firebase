package com.hubrickchallenge.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.actions.SnackbarActions;
import com.hubrickchallenge.android.tools.dagger.components.ApplicationComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject App application;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;

    // LIFECYCLE // ************************************************************************************************************************

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setDagger();
        setButterKnife();
        setToolbar();
        setSnackbar();
        Timber.d("%s created. [Bundle: %s]", getClass().getSimpleName(), savedInstanceState);
    }

    protected abstract int getLayoutId();

    private void setDagger() {
        ApplicationComponent applicationComponent = App.getApplicationComponent();
        applicationComponent.inject(this);
    }

    private void setButterKnife() {
        ButterKnife.bind(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setSnackbar() {
        application.snackbar().setCoordinatorLayout(coordinatorLayout);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Timber.d("%s new intent. [Intent: %s]", getClass().getSimpleName(), intent);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Timber.d("%s restarted.", getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("%s started.", getClass().getSimpleName());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Timber.d("%s instance state restored. [Bundle: %s]", getClass().getSimpleName(), savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("%s resumed.", getClass().getSimpleName());
    }

    @Override
    @CallSuper
    protected void onPause() {
        Timber.d("%s paused.", getClass().getSimpleName());
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Timber.d("%s instance state saved. [Bundle: %s]", getClass().getSimpleName(), outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Timber.d("%s stopped.", getClass().getSimpleName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("%s destroyed.", getClass().getSimpleName());
        super.onDestroy();
    }

    // ACTIONS // **************************************************************************************************************************

    protected NotificationActions notification() {
        return application.notification();
    }

    protected SnackbarActions snackbar() {
        return application.snackbar();
    }

}
