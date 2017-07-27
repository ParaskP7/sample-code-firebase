package com.hubrickchallenge.android.activity.main.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

public interface MainFragmentView extends MvpView {

    void showLoading();

    void addFeedItem(FeedItem feedItem);

    void checkAndShowNotificationButton();

    void updateFeedItems(List<FeedItem> feedItems);

    void showLoadingError();

}
