package com.hubrickchallenge.android.activity.main.view;

import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

public interface FeedItemAdapter {

    void insertData(FeedItem feedItem);

    void changeData(List<FeedItem> feedItems);

}
