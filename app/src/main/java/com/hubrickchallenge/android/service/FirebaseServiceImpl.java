package com.hubrickchallenge.android.service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.mapper.Mapper;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import rx.Observable;

public class FirebaseServiceImpl implements FirebaseService {

    private final DatabaseReference databaseReference;
    private final Mapper<RxFirebaseChildEvent<DataSnapshot>, FeedItem> feedItemMapper;

    public FirebaseServiceImpl(DatabaseReference databaseReference, Mapper<RxFirebaseChildEvent<DataSnapshot>, FeedItem> feedItemMapper) {
        this.databaseReference = databaseReference;
        this.feedItemMapper = feedItemMapper;
    }

    @Override
    public Observable<FeedItem> getFeedItems() {
        return RxFirebaseDatabase.observeChildEvent(databaseReference)
                .map(feedItemMapper::map)
                .filter(feedItem -> feedItem != null);
    }

}
