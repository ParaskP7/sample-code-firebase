package com.hubrickchallenge.android.tools.dagger.modules;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.actions.AppEventActions;
import com.hubrickchallenge.android.actions.AppNotificationActions;
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
    App providesApplication() {
        return application;
    }

    @Provides
    AppEventActions providesAppEventActions() {
        return new AppEventActions();
    }

    @Provides
    AppNotificationActions providesAppNotificationActions() {
        return new AppNotificationActions(application);
    }

    @Provides
    AppSnackbarActions providesAppSnackbarActions() {
        return new AppSnackbarActions(application);
    }

}
