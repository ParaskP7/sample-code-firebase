package com.hubrickchallenge.android.model.mapper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.hubrickchallenge.android.model.FeedItem;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;

import javax.annotation.Nullable;

import timber.log.Timber;

public class FeedItemMapper implements Mapper<RxFirebaseChildEvent<DataSnapshot>, FeedItem> {

    @Nullable
    @Override
    public FeedItem map(RxFirebaseChildEvent<DataSnapshot> rxFirebaseChildEvent) {
        DataSnapshot dataSnapshot = rxFirebaseChildEvent.getValue();
        Timber.d("Child added: %s", dataSnapshot.getValue());
        try {
            return dataSnapshot.getValue(FeedItem.class);
        } catch (DatabaseException de) {
            Timber.w(de, "Database exception.");
            return null;
        }
    }

}
