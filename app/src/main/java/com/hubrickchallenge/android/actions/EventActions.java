package com.hubrickchallenge.android.actions;

import com.hubrickchallenge.android.actions.event.Event;
import com.hubrickchallenge.android.activity.BaseActivity;
import com.hubrickchallenge.android.activity.BaseFragment;

public interface EventActions {

    void register(BaseActivity activity);

    void register(BaseFragment fragment);

    void unregister(BaseActivity activity);

    void unregister(BaseFragment fragment);

    void post(Event event);

}
