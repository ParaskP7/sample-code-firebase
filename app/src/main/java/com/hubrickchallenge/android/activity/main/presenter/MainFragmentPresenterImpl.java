package com.hubrickchallenge.android.activity.main.presenter;

import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class MainFragmentPresenterImpl extends MvpNullObjectBasePresenter<MainFragmentView> implements MainFragmentPresenter {

    private final DatabaseReference databaseReference;
    private final Datastore datastore;
    private final NotificationActions notification;

    @Nullable private Subscription subscription;

    public MainFragmentPresenterImpl(DatabaseReference databaseReference, Datastore datastore, NotificationActions notification) {
        this.databaseReference = databaseReference;
        this.datastore = datastore;
        this.notification = notification;
    }

    @Override
    public void retrieveFeedItems() {
        getView().showLoading();
        subscribeToFeedItems();
    }

    private void subscribeToFeedItems() {
        subscription = RxFirebaseDatabase.observeChildEvent(databaseReference)
                .subscribe(new Action1<RxFirebaseChildEvent<DataSnapshot>>() {
                               @Override
                               public void call(RxFirebaseChildEvent<DataSnapshot> rxFirebaseChildEvent) {
                                   DataSnapshot dataSnapshot = rxFirebaseChildEvent.getValue();
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
                                       MainFragmentPresenterImpl.this.getView().addFeedItem(storedFeedItem);
                                   } else {
                                       boolean isSuccessful = datastore.update().feedItem(feedItem);
                                       if (isSuccessful) {
                                           MainFragmentPresenterImpl.this.getView().updateFeedItems(datastore.get().allFeedItems());
                                       }
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {
                                   Timber.e(throwable, "An error occurred while retrieving feed items.");
                                   getView().showLoadingError();
                               }
                           }
                );
    }

    @Override
    public void checkAndShowNotification(boolean isFragmentStopped) {
        if (isFragmentStopped) {
            notification.show();
        } else {
            getView().checkAndShowNotificationButton();
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        unsubscribeFromFeedItems(retainInstance);
    }

    private void unsubscribeFromFeedItems(boolean retainInstance) {
        if (!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
