package com.hubrickchallenge.android.tools.dagger.components;

import com.hubrickchallenge.android.App;

public class ComponentFactory {

    public static MainFragmentComponent getMainFragmentComponent() {
        return DaggerMainFragmentComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .build();
    }

    private ComponentFactory() {
        throw new AssertionError();
    }

}
