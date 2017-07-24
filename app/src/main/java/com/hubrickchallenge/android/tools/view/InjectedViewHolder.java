package com.hubrickchallenge.android.tools.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubrickchallenge.android.App;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Convenience class for injecting the views into the view holder. All view holders should extend this.
 */
public class InjectedViewHolder extends RecyclerView.ViewHolder {

    @Inject protected App application;

    public InjectedViewHolder(View itemView) {
        super(itemView);
        App.getApplicationComponent().inject(this);
        ButterKnife.bind(this, itemView);
    }

}
