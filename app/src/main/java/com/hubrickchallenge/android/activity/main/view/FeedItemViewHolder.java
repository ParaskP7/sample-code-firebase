package com.hubrickchallenge.android.activity.main.view;

import android.view.View;
import android.widget.TextView;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.tools.view.InjectedViewHolder;
import com.hubrickchallenge.android.util.time.TimeUtil;

import butterknife.BindView;

class FeedItemViewHolder extends InjectedViewHolder {

    @BindView(R.id.authorDisplayNameTextView) TextView authorDisplayNameTextView;
    @BindView(R.id.updatedAtTextView) TextView updatedAtTextView;
    @BindView(R.id.plainTitleTextView) TextView plainTitleTextView;
    @BindView(R.id.plainContentPreviewTextView) TextView plainContentPreviewTextView;
    @BindView(R.id.likeTextView) TextView likeTextView;
    @BindView(R.id.commentTextView) TextView commentTextView;
    @BindView(R.id.shareTextView) TextView shareTextView;

    FeedItemViewHolder(View itemView) {
        super(itemView);
    }

    void bindFeedItem(FeedItem feedItem) {
        authorDisplayNameTextView.setText(feedItem.getAuthor().getDisplayName());
        updatedAtTextView.setText(TimeUtil.getDateTimeDifference(application, feedItem.getUpdatedAt()));
        plainTitleTextView.setText(feedItem.getPayload().getPlainTitle());
        plainContentPreviewTextView.setText(feedItem.getPayload().getPlainContentPreview());
        likeTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getLike()));
        commentTextView.setText(String.valueOf(feedItem.getPayload().getStats().getCommentStats().getCount()));
        shareTextView.setText(String.valueOf(feedItem.getPayload().getStats().getReactionStats().getCounts().getShare()));
    }

}
