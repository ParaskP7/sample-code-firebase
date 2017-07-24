package com.hubrickchallenge.android.activity.main.view;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import javax.annotation.Nullable;

import timber.log.Timber;

public class MainFragmentViewState implements ViewState<MainFragmentView> {

    @Nullable private String helloFirebase;

    @Override
    public void apply(MainFragmentView view, boolean retained) {
        Timber.d("Restoring view state");
        if (helloFirebase != null) {
            Timber.d("Restoring hello firebase: %s", helloFirebase);
            view.displayHelloFirebase(helloFirebase);
        }
    }

    public void saveHelloFirebase(String helloFirebase) {
        Timber.d("Saving hello firebase: %s", helloFirebase);
        this.helloFirebase = helloFirebase;
    }

}
