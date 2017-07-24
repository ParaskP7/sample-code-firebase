package com.hubrickchallenge.android.activity.main.view;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import timber.log.Timber;

public class MainFragmentViewState implements ViewState<MainFragmentView> {

    @Override
    public void apply(MainFragmentView view, boolean retained) {
        Timber.d("Restoring view state");
    }

}
