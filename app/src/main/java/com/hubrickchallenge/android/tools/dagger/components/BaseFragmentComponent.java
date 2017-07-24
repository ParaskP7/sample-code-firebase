package com.hubrickchallenge.android.tools.dagger.components;


import android.support.v4.app.Fragment;

/**
 * Base component that all components injecting into a fragment should extend from.
 */
public interface BaseFragmentComponent<FRAGMENT extends Fragment> {

    void inject(FRAGMENT fragment);

}
