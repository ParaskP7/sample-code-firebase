package com.hubrickchallenge.android.activity.main.presenter;

import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;

import timber.log.Timber;

public class MainFragmentPresenterImpl extends MvpNullObjectBasePresenter<MainFragmentView> implements MainFragmentPresenter {

    private final DatabaseReference databaseReference;
    private final Datastore datastore;
    private final NotificationActions notification;

    @Nullable private ChildEventListener childEventListener;

    public MainFragmentPresenterImpl(DatabaseReference databaseReference, Datastore datastore, NotificationActions notification) {
        this.databaseReference = databaseReference;
        this.datastore = datastore;
        this.notification = notification;
    }

    @Override
    public void subscribeToFeedItems() {
        getView().showLoading();
        addChildEventListener();
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
                    FeedItem storedFeedItem = datastore.store().feedItem(feedItem);
                    getView().displayFeedItem(storedFeedItem);
                } else {
                    datastore.update().feedItem(feedItem);
                    getView().displayFeedItems(datastore.get().allFeedItems());
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
                getView().showLoadingError();
            }

        };
        databaseReference.addChildEventListener(childEventListener);
    }

    @Override
    public void unsubscribeFromFeedItems() {
        removeChildEventListener();
    }

    private void removeChildEventListener() {
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }

    @Override
    public void resubscribeToFeedItems() {
        unsubscribeFromFeedItems();
        subscribeToFeedItems();
    }

    @Override
    public void checkAndShowNotification(boolean isFragmentStopped) {
        if (isFragmentStopped) {
            notification.show();
        } else {
            getView().checkAndShowNotificationButton();
        }
    }

}
