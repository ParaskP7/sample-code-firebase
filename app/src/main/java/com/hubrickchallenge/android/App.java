package com.hubrickchallenge.android;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.hubrickchallenge.android.actions.AppEventActions;
import com.hubrickchallenge.android.actions.AppNotificationActions;
import com.hubrickchallenge.android.actions.AppSnackbarActions;
import com.hubrickchallenge.android.actions.EventActions;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.actions.SnackbarActions;
import com.hubrickchallenge.android.tools.dagger.components.ApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.components.DaggerApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.modules.ApplicationModule;
import com.hubrickchallenge.android.tools.dagger.modules.DatastoreModule;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;

public class App extends MultiDexApplication {

    private static ApplicationComponent applicationComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); // NOTE: This enables the "proxy" trick on the vector images.
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Inject AppEventActions appEventActions;
    @Inject AppNotificationActions appNotificationActions;
    @Inject AppSnackbarActions appSnackbarActions;

    @Override
    public void onCreate() {
        super.onCreate();
        initJodaTime();
        initTimber();
        initDagger();
        initRealm();
        Timber.i("Firebase application created!");
    }

    private void initJodaTime() {
        JodaTimeAndroid.init(this);
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .datastoreModule(new DatastoreModule())
                .build();
        applicationComponent.inject(this);
    }

    private void initRealm() {
        Realm.init(this);
    }

    // GET // ******************************************************************************************************************************

    public Realm getDefaultRealmInstance() {
        return Realm.getDefaultInstance();
    }

    // ACTIONS // **************************************************************************************************************************

    public EventActions event() {
        return appEventActions;
    }

    public NotificationActions notification() {
        return appNotificationActions;
    }

    public SnackbarActions snackbar() {
        return appSnackbarActions;
    }

}
