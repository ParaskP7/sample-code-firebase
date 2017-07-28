package com.hubrickchallenge.android.activity.main.view;

import com.hubrickchallenge.android.GeneralTestHelper;
import com.hubrickchallenge.android.PreconfiguredRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class FeedItemAdapterImplTest extends GeneralTestHelper {

    private FeedItemAdapterImpl feedItemAdapter;

    @Before
    public void setUp() {
        feedItemAdapter = new FeedItemAdapterImpl();
    }

    @Test
    public void WHEN_data_is_inserted_THEN_this_is_added_to_the_list_of_feed_items() {
        assertThat(feedItemAdapter.getData()).isEmpty();

        feedItemAdapter.insertData(testAddFeedItem);

        assertThat(feedItemAdapter.getData()).isEqualTo(getTestFeedItems(testAddFeedItem));
    }

    @Test
    public void WHEN_data_is_changed_THEN_this_is_reflected_on_the_list_of_feed_items() {
        assertThat(feedItemAdapter.getData()).isEmpty();

        feedItemAdapter.changeData(testFeedItems);

        assertThat(feedItemAdapter.getData()).isEqualTo(testFeedItems);
    }

}
