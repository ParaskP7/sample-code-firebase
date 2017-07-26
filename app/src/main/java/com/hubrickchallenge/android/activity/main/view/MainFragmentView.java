package com.hubrickchallenge.android.activity.main.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

public interface MainFragmentView extends MvpView {

    void displayFeedItem(FeedItem feedItem);

    void checkAndShowNotificationButton();

    void displayFeedItems(List<FeedItem> feedItems);

}
