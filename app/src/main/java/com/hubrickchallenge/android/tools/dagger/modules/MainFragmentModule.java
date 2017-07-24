package com.hubrickchallenge.android.tools.dagger.modules;

import com.google.firebase.database.FirebaseDatabase;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenterImpl;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    MainFragmentPresenter providesMainFragmentPresenter() {
        return new MainFragmentPresenterImpl();
    }

    @Provides
    MainFragmentViewState providesMainFragmentViewState() {
        return new MainFragmentViewState();
    }

    @Provides
    FeedItemAdapter providesFeedItemAdapter() {
        return new FeedItemAdapter(FirebaseDatabase.getInstance().getReference());
    }

}
