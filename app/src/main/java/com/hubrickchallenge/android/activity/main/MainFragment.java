package com.hubrickchallenge.android.activity.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.BaseFragment;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.view.FeedItemAdapter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.tools.dagger.components.ComponentFactory;
import com.hubrickchallenge.android.tools.dagger.components.MainFragmentComponent;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends BaseFragment<
        MainFragmentComponent,
        MainFragmentView,
        MainFragmentPresenter,
        MainFragmentViewState>
        implements MainFragmentView {

    @Inject FeedItemAdapter feedItemAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainFragmentComponent constructComponent() {
        return ComponentFactory.getMainFragmentComponent();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setAdapter(feedItemAdapter);
    }

}
