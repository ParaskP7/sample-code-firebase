package com.hubrickchallenge.android.tools.view;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Perform operations on a list of views.
 */
public final class ButterKnifeActions {

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_GONE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setVisibility(View.GONE);
        }
    };

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_VISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };

    private ButterKnifeActions() {
        throw new AssertionError();
    }

}
