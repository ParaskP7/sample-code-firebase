package com.hubrickchallenge.android.activity.main.view;

import com.hubrickchallenge.android.model.FeedItem;

import java.util.List;

public interface FeedItemAdapter {

    void setData(FeedItem feedItem);

    void setData(List<FeedItem> feedItems);

}
