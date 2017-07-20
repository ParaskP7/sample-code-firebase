package com.hubrickchallenge.android.tools.dagger.components;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.tools.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

}
