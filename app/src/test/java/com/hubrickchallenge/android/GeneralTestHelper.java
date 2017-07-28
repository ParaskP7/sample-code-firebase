package com.hubrickchallenge.android;

import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.datastore.DatastoreAddActions;
import com.hubrickchallenge.android.datastore.DatastoreGetActions;
import com.hubrickchallenge.android.datastore.DatastoreStoreActions;
import com.hubrickchallenge.android.datastore.DatastoreUpdateActions;
import com.hubrickchallenge.android.model.Author;
import com.hubrickchallenge.android.model.AvatarImage;
import com.hubrickchallenge.android.model.CommentStats;
import com.hubrickchallenge.android.model.Counts;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;
import com.hubrickchallenge.android.model.HeadLineImage;
import com.hubrickchallenge.android.model.Payload;
import com.hubrickchallenge.android.model.ReactionStats;
import com.hubrickchallenge.android.model.Stats;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GeneralTestHelper {

    private static final String TEST_FEED_ITEM_ID = "1";
    private static final String TEST_PAYLOAD_PLAIN_TITLE = "Test Plain Title";
    private static final String TEST_PAYLOAD_PLAIN_CONTENT_PREVIEW = "Test Plain Content Preview";
    private static final String TEST_PATH = "Test Path";
    private static final Long TEST_COMMENT_STATS_COUNT = 1L;
    private static final Long TEST_COUNT_LIKE = 1L;
    private static final Long TEST_COUNT_SHARE = 1L;
    private static final String TEST_HEAD_LINE_IMAGE_URL = "url";
    private static final String TEST_HEAD_LINE_IMAGE_MIME_TYPE = "image/jpeg";
    private static final String TEST_AUTHOR_NAME = "Name";
    private static final String TEST_AUTHOR_DISPLAY_NAME = "Test Display Name";
    private static final String TEST_AVATAR_IMAGE_URL = "url";
    private static final String TEST_AVATAR_IMAGE_MIME_TYPE = "image/png";

    protected static final RuntimeException TEST_THROWABLE = new RuntimeException("Test Error");

    // Model specific fields.
    protected FeedItem testAddFeedItem = getTestFeedItem(FeedItemType.ADD);
    protected FeedItem testUpdateFeedItem = getTestFeedItem(FeedItemType.UPDATE);
    protected FeedItem testDeleteFeedItem = getTestFeedItem(FeedItemType.DELETE);
    protected List<FeedItem> testFeedItems = getTestFeedItems();

    // Datastore specific fields.
    @Mock protected Datastore datastoreMock;
    @Mock protected DatastoreStoreActions datastoreStoreActionsMock;
    @Mock protected DatastoreAddActions datastoreAddActionsMock;
    @Mock protected DatastoreGetActions datastoreGetActionsMock;
    @Mock protected DatastoreUpdateActions datastoreUpdateActionsMock;

    protected void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        setUpDatastoreMocks();
    }

    // MODEL // ****************************************************************************************************************************

    private FeedItem getTestFeedItem(FeedItemType feedItemType) {
        FeedItem testFeedItem = new FeedItem();
        testFeedItem.setId(TEST_FEED_ITEM_ID);
        testFeedItem.setType(feedItemType.getType());
        testFeedItem.setPayload(getTestPayLoad());
        testFeedItem.setAuthor(getTestAuthor());
        return testFeedItem;
    }

    private Payload getTestPayLoad() {
        Payload testPayload = new Payload();
        testPayload.setStats(getTestState());
        testPayload.setPlainTitle(TEST_PAYLOAD_PLAIN_TITLE);
        testPayload.setPlainContentPreview(TEST_PAYLOAD_PLAIN_CONTENT_PREVIEW);
        testPayload.setPath(TEST_PATH);
        testPayload.setHeadLineImage(getTestHeadLineImage());
        return testPayload;
    }

    private Stats getTestState() {
        Stats testStats = new Stats();
        testStats.setCommentStats(getTestCommentStats());
        testStats.setReactionStats(getTestReactionStats());
        return testStats;
    }

    private CommentStats getTestCommentStats() {
        CommentStats testCommentStats = new CommentStats();
        testCommentStats.setCount(TEST_COMMENT_STATS_COUNT);
        return testCommentStats;
    }

    private ReactionStats getTestReactionStats() {
        ReactionStats testReactionStats = new ReactionStats();
        testReactionStats.setCounts(getTestCounts());
        return testReactionStats;
    }

    private Counts getTestCounts() {
        Counts testCounts = new Counts();
        testCounts.setLike(TEST_COUNT_LIKE);
        testCounts.setShare(TEST_COUNT_SHARE);
        return testCounts;
    }

    private HeadLineImage getTestHeadLineImage() {
        HeadLineImage testHeadLineImage = new HeadLineImage();
        testHeadLineImage.setUrl(TEST_HEAD_LINE_IMAGE_URL);
        testHeadLineImage.setMimeType(TEST_HEAD_LINE_IMAGE_MIME_TYPE);
        return testHeadLineImage;
    }

    private Author getTestAuthor() {
        Author testAuthor = new Author();
        testAuthor.setName(TEST_AUTHOR_NAME);
        testAuthor.setDisplayName(TEST_AUTHOR_DISPLAY_NAME);
        testAuthor.setAvatarImage(getTestAvatarImage());
        return testAuthor;
    }

    private AvatarImage getTestAvatarImage() {
        AvatarImage testAvatarImage = new AvatarImage();
        testAvatarImage.setUrl(TEST_AVATAR_IMAGE_URL);
        testAvatarImage.setMimeType(TEST_AVATAR_IMAGE_MIME_TYPE);
        return testAvatarImage;
    }

    // DATASTORE // ************************************************************************************************************************

    private void setUpDatastoreMocks() {
        when(datastoreMock.store()).thenReturn(datastoreStoreActionsMock);
        when(datastoreMock.add()).thenReturn(datastoreAddActionsMock);
        when(datastoreMock.get()).thenReturn(datastoreGetActionsMock);
        when(datastoreMock.update()).thenReturn(datastoreUpdateActionsMock);
    }

    private List<FeedItem> getTestFeedItems() {
        List<FeedItem> testFeedItems = new ArrayList<>(3);
        testFeedItems.add(testAddFeedItem);
        testFeedItems.add(testUpdateFeedItem);
        testFeedItems.add(testDeleteFeedItem);
        return testFeedItems;
    }

}
