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
import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class FeedItemAdapterImpl extends RecyclerView.Adapter<FeedItemViewHolder> implements FeedItemAdapter {

    @Inject Datastore datastore;

    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    private List<FeedItem> feedItems = new ArrayList<>();

    public FeedItemAdapterImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        addChildEventListener();
        Timber.d("%s attached.", getClass().getSimpleName());
    }

    private void addChildEventListener() {
        childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Timber.d("Child added: %s", dataSnapshot.getValue());
                try {
                    FeedItem feedItem = dataSnapshot.getValue(FeedItem.class);
                    Timber.i("Feed item added: %s", feedItem);
                    insertOrChangeData(feedItem);
                } catch (DatabaseException de) {
                    Timber.w(de, "Database exception.");
                }
            }

            private void insertOrChangeData(FeedItem feedItem) {
                FeedItemType feedItemType = FeedItemType.fromType(feedItem.getType());
                if (feedItemType == FeedItemType.ADD) {
                    feedItems.add(datastore.store().feedItem(feedItem));
                    notifyItemInserted(feedItems.size() - 1);
                } else {
                    datastore.update().feedItem(feedItem);
                    feedItems = new ArrayList<>(datastore.get().allFeedItems());
                    notifyDataSetChanged();
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

        };
        databaseReference.addChildEventListener(childEventListener);
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
        databaseReference.removeEventListener(childEventListener);
        super.onDetachedFromRecyclerView(recyclerView);
    }

}
