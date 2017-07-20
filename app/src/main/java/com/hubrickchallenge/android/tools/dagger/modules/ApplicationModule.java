package com.hubrickchallenge.android.tools.dagger.modules;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.actions.AppSnackbarActions;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    AppSnackbarActions providesAppSnackbarActions() {
        return new AppSnackbarActions(application);
    }

}
