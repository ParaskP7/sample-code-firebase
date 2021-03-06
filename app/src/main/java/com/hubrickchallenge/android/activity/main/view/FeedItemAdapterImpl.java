package com.hubrickchallenge.android.activity.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.util.testing.ForTestingPurposes;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class FeedItemAdapterImpl extends RecyclerView.Adapter<FeedItemViewHolder> implements FeedItemAdapter {

    public static final int POSITION_TOP = 0;

    private List<FeedItem> feedItems = new ArrayList<>();

    public FeedItemAdapterImpl() {
        this.feedItems = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Timber.d("%s attached.", getClass().getSimpleName());
    }

    @Override
    public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_feed, parent, false);
        return new FeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedItemViewHolder feedItemViewHolder, int position) {
        FeedItem feedItem = feedItems.get(position);
        feedItemViewHolder.bindFeedItem(feedItem);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Timber.d("%s detached.", getClass().getSimpleName());
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void insertData(FeedItem feedItem) {
        Timber.v("Inserting data to adapter.");
        if (!feedItems.contains(feedItem)) {
            feedItems.add(POSITION_TOP, feedItem);
            notifyItemInserted(POSITION_TOP);
        }
    }

    @Override
    public void changeData(List<FeedItem> feedItems) {
        Timber.v("Changing adapter with data.");
        this.feedItems = new ArrayList<>(feedItems);
        notifyDataSetChanged();
    }

    @ForTestingPurposes
    List<FeedItem> getData() {
        return feedItems;
    }

}
