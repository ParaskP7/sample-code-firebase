package com.hubrickchallenge.android;

import android.support.multidex.MultiDexApplication;

import com.hubrickchallenge.android.actions.AppSnackbarActions;
import com.hubrickchallenge.android.actions.SnackbarActions;
import com.hubrickchallenge.android.tools.dagger.components.ApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.components.DaggerApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.modules.ApplicationModule;
import com.hubrickchallenge.android.tools.dagger.modules.DatastoreModule;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;

public class App extends MultiDexApplication {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Inject AppSnackbarActions appSnackbarActions;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initDagger();
        initRealm();
        Timber.i("Firebase application created!");
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

    public SnackbarActions snackbar() {
        return appSnackbarActions;
    }

}
