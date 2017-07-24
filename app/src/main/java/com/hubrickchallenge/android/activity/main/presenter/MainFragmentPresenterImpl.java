package com.hubrickchallenge.android.activity.main.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.datastore.Datastore;

public class MainFragmentPresenterImpl extends MvpNullObjectBasePresenter<MainFragmentView> implements MainFragmentPresenter {

    private final Datastore datastore;

    public MainFragmentPresenterImpl(Datastore datastore) {
        this.datastore = datastore;
    }

}
