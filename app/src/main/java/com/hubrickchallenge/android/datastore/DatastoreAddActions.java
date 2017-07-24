package com.hubrickchallenge.android.datastore;


import com.hubrickchallenge.android.model.FeedItem;

import io.realm.Realm;
import timber.log.Timber;

public class DatastoreAddActions implements AddActions {

    private Realm realm;

    public DatastoreAddActions(Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean feedItem(FeedItem feedItem) {
        realm.beginTransaction();
        realm.copyToRealm(feedItem);
        realm.commitTransaction();
        Timber.d("New feed item added: %s", feedItem);
        return false;
    }

}
