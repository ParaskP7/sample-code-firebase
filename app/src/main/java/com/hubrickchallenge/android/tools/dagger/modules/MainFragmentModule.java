package com.hubrickchallenge.android.tools.dagger.modules;

import com.google.firebase.database.FirebaseDatabase;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenterImpl;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.datastore.Datastore;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    MainFragmentPresenter providesMainFragmentPresenter(Datastore datastore) {
        return new MainFragmentPresenterImpl(datastore);
    }

    @Provides
    MainFragmentViewState providesMainFragmentViewState() {
        return new MainFragmentViewState();
    }

    @Provides
    FeedItemAdapterImpl providesFeedItemAdapter() {
        return new FeedItemAdapterImpl(FirebaseDatabase.getInstance().getReference());
    }

}
