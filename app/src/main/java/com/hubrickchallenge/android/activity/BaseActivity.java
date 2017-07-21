package com.hubrickchallenge.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.actions.SnackbarActions;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setButterKnife();
        setToolbar();
        setSnackbar();
    }

    protected abstract int getLayoutId();

    private void setButterKnife() {
        ButterKnife.bind(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setSnackbar() {
        ((App) getApplication()).snackbar().setCoordinatorLayout(coordinatorLayout);
    }

    protected SnackbarActions snackbar() {
        return ((App) getApplication()).snackbar();
    }

}
