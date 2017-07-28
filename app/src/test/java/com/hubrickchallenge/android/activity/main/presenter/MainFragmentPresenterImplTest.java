package com.hubrickchallenge.android.activity.main.presenter;

import com.hubrickchallenge.android.GeneralTestHelper;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.service.FirebaseService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import rx.Observable;
import rx.Subscription;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MainFragmentPresenterImplTest extends GeneralTestHelper {

    @Mock private FirebaseService firebaseServiceMock;
    @Mock private NotificationActions notificationMock;

    private MainFragmentPresenterImpl mainFragmentPresenter;
    @Mock private MainFragmentView mainFragmentViewMock;
    @Mock private Subscription subscriptionMock;

    @Before
    public void setUp() {
        setUpMocks();
        mainFragmentPresenter = new MainFragmentPresenterImpl(firebaseServiceMock, datastoreMock, notificationMock);
        mainFragmentPresenter.attachView(mainFragmentViewMock);
    }

    @Test
    public void WHEN_feed_items_are_being_retrieved_THEN_loading_is_shown() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testAddFeedItem));

        mainFragmentPresenter.retrieveFeedItems();

        verify(mainFragmentViewMock).showLoading();
    }

    @Test
    public void WHEN_feed_items_are_being_retrieved_THEN_a_subscription_is_created() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testAddFeedItem));
        assertThat(mainFragmentPresenter.subscription).isNull();

        mainFragmentPresenter.retrieveFeedItems();

        assertThat(mainFragmentPresenter.subscription).isNotNull();
    }

    @Test
    public void WHEN_an_add_feed_item_is_being_retrieved_THEN_this_feed_item_is_stored() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testAddFeedItem));

        mainFragmentPresenter.retrieveFeedItems();

        verify(datastoreStoreActionsMock).feedItem(testAddFeedItem);
    }

    @Test
    public void WHEN_an_add_feed_item_is_Being_retrieved_THEN_this_feed_item_is_added_to_the_view() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testAddFeedItem));
        when(datastoreStoreActionsMock.feedItem(testAddFeedItem)).thenReturn(testAddFeedItem);

        mainFragmentPresenter.retrieveFeedItems();

        verify(mainFragmentViewMock).addFeedItem(testAddFeedItem);
    }

    @Test
    public void WHEN_an_update_feed_item_is_being_retrieved_THEN_this_feed_item_is_updated() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testUpdateFeedItem));

        mainFragmentPresenter.retrieveFeedItems();

        verify(datastoreUpdateActionsMock).feedItem(testUpdateFeedItem);
    }

    @Test
    public void WHEN_an_update_feed_item_is_Being_retrieved_THEN_all_feed_item_are_updated_on_the_view() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testUpdateFeedItem));
        when(datastoreUpdateActionsMock.feedItem(testUpdateFeedItem)).thenReturn(true);
        when(datastoreGetActionsMock.allFeedItems()).thenReturn(testFeedItems);

        mainFragmentPresenter.retrieveFeedItems();

        verify(mainFragmentViewMock).updateFeedItems(testFeedItems);
    }

    @Test
    public void WHEN_an_delete_feed_item_is_being_retrieved_THEN_this_feed_item_is_updated() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testDeleteFeedItem));

        mainFragmentPresenter.retrieveFeedItems();

        verify(datastoreUpdateActionsMock).feedItem(testDeleteFeedItem);
    }

    @Test
    public void WHEN_an_delete_feed_item_is_Being_retrieved_THEN_all_feed_item_are_updated_on_the_view() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.just(testDeleteFeedItem));
        when(datastoreUpdateActionsMock.feedItem(testDeleteFeedItem)).thenReturn(true);
        when(datastoreGetActionsMock.allFeedItems()).thenReturn(testFeedItems);

        mainFragmentPresenter.retrieveFeedItems();

        verify(mainFragmentViewMock).updateFeedItems(testFeedItems);
    }

    @Test
    public void WHEN_feed_items_fail_to_be_retrieved_THEN_loading_error_is_shown() {
        when(firebaseServiceMock.getFeedItems()).thenReturn(Observable.error(TEST_THROWABLE));

        mainFragmentPresenter.retrieveFeedItems();

        verify(mainFragmentViewMock).showLoadingError();
    }

    @Test
    public void GIVEN_the_fragment_is_stopped_WHEN_show_notification_is_triggered_THEN_a_notification_is_shown() {
        mainFragmentPresenter.checkAndShowNotification(true);

        verify(notificationMock).show();
    }

    @Test
    public void GIVEN_the_fragment_is_not_stopped_WHEN_show_notification_is_triggered_THEN_the_notification_button_is_shown_on_the_view() {
        mainFragmentPresenter.checkAndShowNotification(false);

        verify(mainFragmentViewMock).checkAndShowNotificationButton();
    }

    @Test
    public void GIVEN_the_subscription_is_still_subscribed_WHEN_the_view_is_detached_THEN_the_subscription_is_unsubscribed() {
        mainFragmentPresenter.subscription = subscriptionMock;
        when(subscriptionMock.isUnsubscribed()).thenReturn(false);

        mainFragmentPresenter.detachView(false);

        verify(subscriptionMock).unsubscribe();
    }

    @Test
    public void GIVEN_the_subscription_is_already_unsubscribed_WHEN_the_view_is_detached_THEN_the_subscription_is_not_unsubscribed() {
        mainFragmentPresenter.subscription = subscriptionMock;
        when(subscriptionMock.isUnsubscribed()).thenReturn(true);

        mainFragmentPresenter.detachView(false);

        verify(subscriptionMock, times(0)).unsubscribe();
    }

}
