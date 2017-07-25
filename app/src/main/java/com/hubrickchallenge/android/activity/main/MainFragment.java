package com.hubrickchallenge.android.activity.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.BaseFragment;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.tools.dagger.components.ComponentFactory;
import com.hubrickchallenge.android.tools.dagger.components.MainFragmentComponent;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainFragment extends BaseFragment<
        MainFragmentComponent,
        MainFragmentView,
        MainFragmentPresenter,
        MainFragmentViewState>
        implements MainFragmentView {

    private static final int DIRECTION_UPWARDS = 0;

    @Inject FeedItemAdapterImpl feedItemAdapterImpl;

    @BindView(R.id.notificationButton) AppCompatButton notificationButton;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private boolean isStooped;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainFragmentComponent constructComponent() {
        return ComponentFactory.getMainFragmentComponent();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    @Override
    protected void onFirstCreate() {
        super.onFirstCreate();
        getPresenter().subscribeToFeedItems();
    }

    private void setRecyclerView() {
        recyclerView.setAdapter(feedItemAdapterImpl);
    }

    @Override
    public void onStart() {
        super.onStart();
        isStooped = false;
        checkAndShowNotificationButton();
    }

    @Override
    public void onStop() {
        super.onStop();
        isStooped = true;
    }

    @Override
    public void displayFeedItem(FeedItem feedItem) {
        Timber.i("Displaying feed item: %s", feedItem);
        getViewState().saveFeedItem(feedItem);
        showNotification();
        feedItemAdapterImpl.setData(feedItem);
    }

    private void showNotification() {
        if (isStooped) {
            notification().show();
        } else {
            checkAndShowNotificationButton();
        }
    }

    private void checkAndShowNotificationButton() {
        if (recyclerView.canScrollVertically(DIRECTION_UPWARDS)) {
            notificationButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayFeedItems(List<FeedItem> feedItems) {
        Timber.i("Displaying feed items: %s", feedItems);
        getViewState().saveFeedItems(feedItems);
        feedItemAdapterImpl.setData(feedItems);
    }

    @OnClick(R.id.notificationButton)
    void onNotificationButtonClick() {
        notificationButton.setVisibility(View.INVISIBLE);
        recyclerView.smoothScrollToPosition(DIRECTION_UPWARDS);
    }

}
