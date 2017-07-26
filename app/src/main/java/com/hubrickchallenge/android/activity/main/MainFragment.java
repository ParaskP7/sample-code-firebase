package com.hubrickchallenge.android.activity.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.BaseFragment;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.tools.dagger.components.ComponentFactory;
import com.hubrickchallenge.android.tools.dagger.components.MainFragmentComponent;
import com.hubrickchallenge.android.tools.view.ButterKnifeActions;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainFragment extends BaseFragment<
        MainFragmentComponent,
        MainFragmentView,
        MainFragmentPresenter,
        MainFragmentViewState>
        implements MainFragmentView {

    private static final int DIRECTION_UPWARDS = 0;

    @Inject FeedItemAdapterImpl feedItemAdapterImpl;

    @BindView(R.id.notificationButton) AppCompatButton notificationButton;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @BindView(R.id.lottieAnimationView) LottieAnimationView lottieAnimationView;
    @BindViews({R.id.reloadTextView, R.id.reloadButton, R.id.lottieAnimationView}) List<View> errorViews;

    private boolean isStopped;

    // LIFECYCLE // ************************************************************************************************************************

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
        isStopped = false;
        checkAndShowNotificationButton();
    }

    @Override
    public void onStop() {
        super.onStop();
        isStopped = true;
    }

    // VIEW // *****************************************************************************************************************************

    @Override
    public void showLoading() {
        Timber.d("Showing loading.");
        progressBar.setVisibility(VISIBLE);
        ButterKnife.apply(errorViews, ButterKnifeActions.SET_VISIBILITY_TO_GONE);
    }

    @Override
    public void displayFeedItem(FeedItem feedItem) {
        Timber.i("Displaying feed item: %s", feedItem);
        getViewState().saveFeedItem(feedItem);
        progressBar.setVisibility(View.GONE);
        getPresenter().checkAndShowNotification(isStopped);
        feedItemAdapterImpl.setData(feedItem);
    }

    @Override
    public void checkAndShowNotificationButton() {
        if (recyclerView.canScrollVertically(DIRECTION_UPWARDS)) {
            notificationButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayFeedItems(List<FeedItem> feedItems) {
        Timber.i("Displaying feed items: %s", feedItems);
        getViewState().saveFeedItems(feedItems);
        progressBar.setVisibility(View.GONE);
        feedItemAdapterImpl.setData(feedItems);
    }

    @Override
    public void showLoadingError() {
        Timber.v("Showing loading error.");
        progressBar.setVisibility(GONE);
        lottieAnimationView.playAnimation();
        ButterKnife.apply(errorViews, ButterKnifeActions.SET_VISIBILITY_TO_VISIBLE);
    }

    // CLICK EVENTS // *********************************************************************************************************************

    @OnClick(R.id.notificationButton)
    void onNotificationButtonClick() {
        notificationButton.setVisibility(View.GONE);
        recyclerView.smoothScrollToPosition(DIRECTION_UPWARDS);
    }

    @OnClick(R.id.reloadButton)
    void onReloadButtonClick() {
        Timber.i("Reload button clicked.");
        lottieAnimationView.cancelAnimation();
        getPresenter().resubscribeToFeedItems();
    }

}
