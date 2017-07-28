package com.hubrickchallenge.android.datastore;


import android.support.annotation.Nullable;

import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import timber.log.Timber;

public class DatastoreGetActions implements GetActions {

    private final Realm realm;

    public DatastoreGetActions(Realm realm) {
        this.realm = realm;
    }

    @Override
    public List<FeedItem> allFeedItems() {
        List<FeedItem> feedItems = realm.where(FeedItem.class)
                .findAll()
                .sort(FeedItem.ID, Sort.DESCENDING);
        if (feedItems.isEmpty()) {
            Timber.d("There are no feed items.");
        }
        return feedItems;
    }

    @Nullable
    @Override
    public FeedItem feedItem(String feedItemId) {
        FeedItem existingFeedItem = realm.where(FeedItem.class)
                .equalTo(FeedItem.ID, feedItemId)
                .findFirst();
        if (existingFeedItem != null) {
            Timber.d("Existing feed item retrieved: %s", existingFeedItem);
        }
        return existingFeedItem;
    }

}
