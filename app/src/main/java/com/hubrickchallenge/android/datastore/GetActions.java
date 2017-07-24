package com.hubrickchallenge.android.datastore;

import android.support.annotation.Nullable;

import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

public interface GetActions {

    List<FeedItem> allFeedItems();

    @Nullable
    FeedItem feedItem(String feedItemId);

}
