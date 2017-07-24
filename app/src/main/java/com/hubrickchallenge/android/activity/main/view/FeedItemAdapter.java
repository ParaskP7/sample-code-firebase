package com.hubrickchallenge.android.activity.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemViewHolder> {

    private List<FeedItem> feedItems = new ArrayList<>();

    public FeedItemAdapter(DatabaseReference databaseReference) {
        addChildEventListener(databaseReference);
    }

    private void addChildEventListener(DatabaseReference databaseReference) {
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                try {
                    FeedItem feedItem = dataSnapshot.getValue(FeedItem.class);
                    Timber.d("Child added: %s", feedItem);
                    feedItems.add(feedItem);
                    notifyItemInserted(feedItems.size() - 1);
                } catch (DatabaseException de) {
                    Timber.w(de, "Database exception.");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Timber.d("Child changed: %s", dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Timber.d("Child removed: %s", dataSnapshot.getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Timber.d("Child moved: %s", dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Timber.w(databaseError.toException(), "Database error.");
            }

        });
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

}
