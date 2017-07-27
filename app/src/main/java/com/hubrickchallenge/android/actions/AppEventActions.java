package com.hubrickchallenge.android.actions;

import com.hubrickchallenge.android.actions.event.Event;
import com.hubrickchallenge.android.activity.BaseActivity;
import com.hubrickchallenge.android.activity.BaseFragment;

import org.greenrobot.eventbus.EventBus;

public class AppEventActions implements EventActions {

    @Override
    public void register(BaseActivity activity) {
        EventBus.getDefault().register(activity);
    }

    @Override
    public void register(BaseFragment fragment) {
        EventBus.getDefault().register(fragment);
    }

    @Override
    public void unregister(BaseActivity activity) {
        EventBus.getDefault().unregister(activity);
    }

    @Override
    public void unregister(BaseFragment fragment) {
        EventBus.getDefault().unregister(fragment);
    }

    @Override
    public void post(Event event) {
        EventBus.getDefault().post(event);
    }

}
