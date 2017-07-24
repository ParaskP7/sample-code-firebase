package com.hubrickchallenge.android.activity.main.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;

import static com.hubrickchallenge.android.util.GeneralUtil.SPACE;

public class MainFragmentPresenterImpl extends MvpNullObjectBasePresenter<MainFragmentView> implements MainFragmentPresenter {

    @Override
    public void getHelloFirebase(String previousHelloFirebase, String newHelloFirebase) {
        getView().displayHelloFirebase(previousHelloFirebase + SPACE + newHelloFirebase);
    }

}
