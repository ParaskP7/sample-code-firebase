package com.hubrickchallenge.android.service;

import com.hubrickchallenge.android.model.FeedItem;

import rx.Observable;

public interface FirebaseService {

    Observable<FeedItem> getFeedItems();

}
