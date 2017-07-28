package com.hubrickchallenge.android.activity.main.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;
import com.hubrickchallenge.android.service.FirebaseService;

import rx.Subscription;
import timber.log.Timber;

public class MainFragmentPresenterImpl extends MvpNullObjectBasePresenter<MainFragmentView> implements MainFragmentPresenter {

    private final FirebaseService firebaseService;
    private final Datastore datastore;
    private final NotificationActions notification;

    @VisibleForTesting
    @Nullable Subscription subscription;

    public MainFragmentPresenterImpl(FirebaseService firebaseService, Datastore datastore, NotificationActions notification) {
        this.firebaseService = firebaseService;
        this.datastore = datastore;
        this.notification = notification;
    }

    @Override
    public void attachView(@NonNull MainFragmentView view) {
        super.attachView(view);
        Timber.d("%s attached.", getClass().getSimpleName());
    }

    @Override
    public void retrieveFeedItems() {
        getView().showLoading();
        subscribeToFeedItems();
    }

    private void subscribeToFeedItems() {
        Timber.d("Subscribe to feed items.");
        subscription = firebaseService.getFeedItems()
                .subscribe(this::handleFeedItemResponse, this::handleFeedItemError);
    }

    private void handleFeedItemResponse(FeedItem feedItem) {
        Timber.i("Feed item added: %s", feedItem);
        FeedItemType feedItemType = FeedItemType.fromType(feedItem.getType());
        if (feedItemType == FeedItemType.ADD) {
            FeedItem storedFeedItem = datastore.store().feedItem(feedItem);
            getView().addFeedItem(storedFeedItem);
        } else {
            boolean isSuccessful = datastore.update().feedItem(feedItem);
            if (isSuccessful) {
                getView().updateFeedItems(datastore.get().allFeedItems());
            }
        }
    }

    private void handleFeedItemError(Throwable throwable) {
        Timber.e(throwable, "An error occurred while retrieving feed items.");
        getView().showLoadingError();
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
        Timber.d("%s detached.", getClass().getSimpleName());
        super.detachView(retainInstance);
        unsubscribeFromFeedItems(retainInstance);
    }

    private void unsubscribeFromFeedItems(boolean retainInstance) {
        if (!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            Timber.d("Unsubscribe from feed items.");
            subscription.unsubscribe();
        }
    }

}
