package com.hubrickchallenge.android;

import android.app.Application;

import com.hubrickchallenge.android.actions.AppSnackbarActions;
import com.hubrickchallenge.android.actions.SnackbarActions;
import com.hubrickchallenge.android.tools.dagger.components.ApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.components.DaggerApplicationComponent;
import com.hubrickchallenge.android.tools.dagger.modules.ApplicationModule;

import javax.inject.Inject;

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Inject AppSnackbarActions appSnackbarActions;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    // ACTIONS // **************************************************************************************************************************

    public SnackbarActions snackbar() {
        return appSnackbarActions;
    }

}
