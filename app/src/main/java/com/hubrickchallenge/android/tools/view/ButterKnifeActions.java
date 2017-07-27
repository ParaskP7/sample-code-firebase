package com.hubrickchallenge.android.tools.view;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Perform operations on a list of views.
 */
public final class ButterKnifeActions {

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_GONE = (view, index) -> view.setVisibility(View.GONE);

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_VISIBLE = (view, index) -> view.setVisibility(View.VISIBLE);

    private ButterKnifeActions() {
        throw new AssertionError();
    }

}
