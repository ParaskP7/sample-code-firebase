package com.hubrickchallenge.android.activity.main.view;

import android.view.View;
import android.widget.TextView;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.tools.butterknife.InjectedViewHolder;

import butterknife.BindView;

class FeedItemViewHolder extends InjectedViewHolder {

    @BindView(R.id.typeTextView) TextView typeTextView;
    @BindView(R.id.idTextView) TextView idTextView;
    @BindView(R.id.payloadTextView) TextView payloadTextView;
    @BindView(R.id.authorTextView) TextView authorTextView;

    FeedItemViewHolder(View itemView) {
        super(itemView);
    }

    void bindFeedItem(FeedItem feedItem) {
        typeTextView.setText(feedItem.getType());
        idTextView.setText(feedItem.getId());
        payloadTextView.setText(feedItem.getPayload().toString());
        authorTextView.setText(feedItem.getAuthor().toString());
    }

}
