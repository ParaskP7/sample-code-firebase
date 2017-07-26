package com.hubrickchallenge.android.activity.main.view;

import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.hubrickchallenge.android.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl.POSITION_TOP;

public class MainFragmentViewState implements ViewState<MainFragmentView> {

    @Nullable private List<FeedItem> feedItems;

    @Override
    public void apply(MainFragmentView view, boolean retained) {
        Timber.d("Restoring view state");
        if (feedItems != null) {
            Timber.d("Restoring feed items: %s", feedItems);
            view.displayFeedItems(feedItems);
        }
    }

    public void saveFeedItem(FeedItem feedItem) {
        initFeedItems();
        if (!feedItems.contains(feedItem)) {
            Timber.d("Saving feed item: %s", feedItem);
            feedItems.add(POSITION_TOP, feedItem);
        }
    }

    private void initFeedItems() {
        if (feedItems == null) {
            Timber.d("Saving view state");
            feedItems = new ArrayList<>();
        }
    }

    public void saveFeedItems(List<FeedItem> feedItems) {
        initFeedItems();
        Timber.d("Saving feed items: %s", feedItems);
        this.feedItems = new ArrayList<>(feedItems);
    }

}
