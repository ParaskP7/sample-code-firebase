package com.hubrickchallenge.android.tools.dagger.components;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.actions.AppNotificationActions;
import com.hubrickchallenge.android.activity.BaseActivity;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.tools.dagger.modules.ApplicationModule;
import com.hubrickchallenge.android.tools.dagger.modules.DatastoreModule;
import com.hubrickchallenge.android.tools.view.InjectedViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DatastoreModule.class})
public interface ApplicationComponent {

    void inject(App application);

    void inject(BaseActivity baseActivity);

    void inject(InjectedViewHolder injectedViewHolder);

    // Downstream components need these exposed the method name does not matter, only the return type.

    App application();

    AppNotificationActions notification();

    Datastore datastore();

}
