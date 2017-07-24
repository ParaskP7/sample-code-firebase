package com.hubrickchallenge.android.datastore;


import com.hubrickchallenge.android.model.FeedItem;

import io.realm.Realm;
import timber.log.Timber;

public class DatastoreUpdateActions implements UpdateActions {

    private Realm realm;

    public DatastoreUpdateActions(Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean feedItem(FeedItem feedItem) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(feedItem);
        realm.commitTransaction();
        Timber.d("Existing feed item updated: %s", feedItem);
        return true;
    }

}
