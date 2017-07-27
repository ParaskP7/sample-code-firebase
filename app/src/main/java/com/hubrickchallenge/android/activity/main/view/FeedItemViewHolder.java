package com.hubrickchallenge.android.activity.main.view;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.webkit.URLUtil;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;
import com.hubrickchallenge.android.tools.image.ImageLoader;
import com.hubrickchallenge.android.tools.view.InjectedViewHolder;
import com.hubrickchallenge.android.util.time.TimeUtil;

import butterknife.BindColor;
import butterknife.BindView;

class FeedItemViewHolder extends InjectedViewHolder {

    @BindColor(R.color.transparent)
    int background;
    @BindColor(R.color.grey_material_300)
    int deletedBackground;

    @BindView(R.id.cardConstraintLayout) ConstraintLayout cardConstraintLayout;

    @BindView(R.id.avatarImageView) AppCompatImageView avatarImageView;
    @BindView(R.id.authorDisplayNameTextView) AppCompatTextView authorDisplayNameTextView;
    @BindView(R.id.updatedAtTextView) AppCompatTextView updatedAtTextView;

    @BindView(R.id.headlineImageView) AppCompatImageView headlineImageView;
    @BindView(R.id.plainTitleTextView) AppCompatTextView plainTitleTextView;
    @BindView(R.id.plainContentPreviewTextView) AppCompatTextView plainContentPreviewTextView;

    @BindView(R.id.likeImageView) AppCompatImageView likeImageView;
    @BindView(R.id.likeTextView) AppCompatTextView likeTextView;
    @BindView(R.id.commentImageView) AppCompatImageView commentImageView;
    @BindView(R.id.commentTextView) AppCompatTextView commentTextView;
    @BindView(R.id.shareImageView) AppCompatImageView shareImageView;
    @BindView(R.id.shareTextView) AppCompatTextView shareTextView;

    FeedItemViewHolder(View itemView) {
        super(itemView);
    }

    void bindFeedItem(FeedItem feedItem) {
        setCardBackground(feedItem);
        setAvatarImageView(feedItem);
        setTopTextViews(feedItem);
        setHeadlineImageView(feedItem);
        setMiddleTextViews(feedItem);
        setBottomTextViews(feedItem);
    }

    private void setCardBackground(FeedItem feedItem) {
        FeedItemType feedItemType = FeedItemType.fromType(feedItem.getType());
        if (feedItemType != FeedItemType.DELETE) {
            cardConstraintLayout.setBackgroundColor(background);
            likeImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_like_button_image_view));
            commentImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_comment_button_image_view));
            shareImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_share_button_image_view));
        } else {
            cardConstraintLayout.setBackgroundColor(deletedBackground);
            likeImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_like_button_image_view_deleted));
            commentImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_comment_button_image_view_deleted));
            shareImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_share_button_image_view_deleted));
        }
    }

    private void setAvatarImageView(FeedItem feedItem) {
        String url = feedItem.getAuthor().getAvatarImage().getUrl();
        if (URLUtil.isValidUrl(url)) {
            ImageLoader.loadImage(url, avatarImageView);
        } else {
            ImageLoader.loadImage(R.drawable.default_avatar_image_view, avatarImageView);
        }
    }

    private void setTopTextViews(FeedItem feedItem) {
        authorDisplayNameTextView.setText(feedItem.getAuthor().getDisplayName());
        updatedAtTextView.setText(TimeUtil.getDateTimeDifference(application, TimeUtil.dateTimeFromString(feedItem.getUpdatedAt())));
    }

    private void setHeadlineImageView(FeedItem feedItem) {
        String url = feedItem.getPayload().getHeadLineImage().getUrl();
        if (URLUtil.isValidUrl(url)) {
            ImageLoader.loadImage(url, headlineImageView);
        } else {
            ImageLoader.loadImage(R.drawable.default_headline_image_view, headlineImageView);
        }
    }

    private void setMiddleTextViews(FeedItem feedItem) {
        plainTitleTextView.setText(feedItem.getPayload().getPlainTitle());
        plainContentPreviewTextView.setText(feedItem.getPayload().getPlainContentPreview());
    }

    private void setBottomTextViews(FeedItem feedItem) {
        likeTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getLike()));
        commentTextView.setText(String.valueOf(feedItem.getPayload().getStats().getCommentStats().getCount()));
        shareTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getShare()));
    }

}
