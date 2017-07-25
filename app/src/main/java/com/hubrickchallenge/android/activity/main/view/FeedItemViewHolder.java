package com.hubrickchallenge.android.activity.main.view;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.FeedItemType;
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
    @BindView(R.id.iconImageView) ImageView iconImageView;
    @BindView(R.id.authorDisplayNameTextView) TextView authorDisplayNameTextView;
    @BindView(R.id.updatedAtTextView) TextView updatedAtTextView;
    @BindView(R.id.plainTitleTextView) TextView plainTitleTextView;
    @BindView(R.id.plainContentPreviewTextView) TextView plainContentPreviewTextView;
    @BindView(R.id.likeImageView) ImageView likeImageView;
    @BindView(R.id.likeTextView) TextView likeTextView;
    @BindView(R.id.commentImageView) ImageView commentImageView;
    @BindView(R.id.commentTextView) TextView commentTextView;
    @BindView(R.id.shareImageView) ImageView shareImageView;
    @BindView(R.id.shareTextView) TextView shareTextView;

    FeedItemViewHolder(View itemView) {
        super(itemView);
    }

    void bindFeedItem(FeedItem feedItem) {
        setCardBackground(feedItem);
        authorDisplayNameTextView.setText(feedItem.getAuthor().getDisplayName());
        updatedAtTextView.setText(TimeUtil.getDateTimeDifference(application, TimeUtil.dateTimeFromString(feedItem.getUpdatedAt())));
        plainTitleTextView.setText(feedItem.getPayload().getPlainTitle());
        plainContentPreviewTextView.setText(feedItem.getPayload().getPlainContentPreview());
        likeTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getLike()));
        commentTextView.setText(String.valueOf(feedItem.getPayload().getStats().getCommentStats().getCount()));
        shareTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getShare()));
    }

    private void setCardBackground(FeedItem feedItem) {
        FeedItemType feedItemType = FeedItemType.fromType(feedItem.getType());
        if (feedItemType != feedItemType.DELETE) {
            cardConstraintLayout.setBackgroundColor(background);
            iconImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_icon_image_view));
            likeImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_like_button_image_view));
            commentImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_comment_button_image_view));
            shareImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_share_button_image_view));
        } else {
            cardConstraintLayout.setBackgroundColor(deletedBackground);
            iconImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_icon_image_view_deleted));
            likeImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_like_button_image_view_deleted));
            commentImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_comment_button_image_view_deleted));
            shareImageView.setImageDrawable(ContextCompat.getDrawable(application, R.drawable.test_share_button_image_view_deleted));
        }
    }

}
