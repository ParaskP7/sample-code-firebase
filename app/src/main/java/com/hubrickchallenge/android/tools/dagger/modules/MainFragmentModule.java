package com.hubrickchallenge.android.tools.dagger.modules;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.hubrickchallenge.android.actions.AppNotificationActions;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenterImpl;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapterImpl;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.model.FeedItem;
import com.hubrickchallenge.android.model.mapper.FeedItemMapper;
import com.hubrickchallenge.android.model.mapper.Mapper;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    Mapper<RxFirebaseChildEvent<DataSnapshot>, FeedItem> providesFeedItemMapper() {
        return new FeedItemMapper();
    }

    @Provides
    MainFragmentPresenter providesMainFragmentPresenter(Datastore datastore,
                                                        Mapper<RxFirebaseChildEvent<DataSnapshot>, FeedItem> feedItemMapper,
                                                        AppNotificationActions notification) {
        return new MainFragmentPresenterImpl(FirebaseDatabase.getInstance().getReference(), feedItemMapper, datastore, notification);
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
