package com.hubrickchallenge.android.tools.dagger.modules;

import com.google.firebase.database.FirebaseDatabase;
import com.hubrickchallenge.android.actions.AppNotificationActions;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenterImpl;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.mapper.FeedItemMapper;
import com.hubrickchallenge.android.service.FirebaseService;
import com.hubrickchallenge.android.service.FirebaseServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    FirebaseService providesFirebaseService() {
        return new FirebaseServiceImpl(FirebaseDatabase.getInstance().getReference(), new FeedItemMapper());
    }

    @Provides
    MainFragmentPresenter providesMainFragmentPresenter(FirebaseService firebaseService, Datastore datastore,
                                                        AppNotificationActions notification) {
        return new MainFragmentPresenterImpl(firebaseService, datastore, notification);
    }

    @Provides
    MainFragmentViewState providesMainFragmentViewState() {
        return new MainFragmentViewState();
    }

    @Provides
    FeedItemAdapterImpl providesFeedItemAdapter() {
        return new FeedItemAdapterImpl();
    }

}
