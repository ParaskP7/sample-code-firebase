package com.hubrickchallenge.android.activity.main.view;

import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

import timber.log.Timber;

public class MainFragmentViewState implements ViewState<MainFragmentView> {

    @Nullable private List<FeedItem> feedItems;

    @Override
    public void apply(MainFragmentView view, boolean retained) {
        Timber.d("Restoring view state");
        if (feedItems != null) {
            Timber.d("Restoring feed items: %s", feedItems);
        }
    }

    public void saveFeedItems(List<FeedItem> feedItems) {
        Timber.d("Saving feed items: %s", feedItems);
        this.feedItems = feedItems;
    }

}
