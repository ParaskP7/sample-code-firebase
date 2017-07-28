package com.hubrickchallenge.android.datastore;


import com.hubrickchallenge.android.model.FeedItem;

import javax.annotation.Nullable;

import timber.log.Timber;

public class DatastoreStoreActions implements StoreActions {

    private final DatastoreGetActions datastoreGetActions;
    private final DatastoreAddActions datastoreAddActions;

    public DatastoreStoreActions(DatastoreAddActions datastoreAddActions, DatastoreGetActions datastoreGetActions) {
        this.datastoreAddActions = datastoreAddActions;
        this.datastoreGetActions = datastoreGetActions;
    }

    @Override
    public FeedItem feedItem(FeedItem feedItem) {
        @Nullable FeedItem existingFeedItem = datastoreGetActions.feedItem(feedItem.getId());
        if (existingFeedItem == null) {
            datastoreAddActions.feedItem(feedItem);
            return feedItem;
        } else {
            Timber.d("Duplicate feed item added: %s", feedItem);
            return existingFeedItem;
        }
    }

}
