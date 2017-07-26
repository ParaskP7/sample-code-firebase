package com.hubrickchallenge.android.activity.main.presenter;


import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;

public interface MainFragmentPresenter extends MvpPresenter<MainFragmentView> {

    void subscribeToFeedItems();

    void unsubscribeFromFeedItems();

    void checkAndShowNotification(boolean isFragmentStopped);

}
