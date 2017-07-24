package com.hubrickchallenge.android.activity.main;

import android.widget.TextView;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.BaseFragment;
import com.hubrickchallenge.android.activity.main.presenter.MainFragmentPresenter;
import com.hubrickchallenge.android.activity.main.view.MainFragmentView;
import com.hubrickchallenge.android.activity.main.view.MainFragmentViewState;
import com.hubrickchallenge.android.tools.dagger.components.ComponentFactory;
import com.hubrickchallenge.android.tools.dagger.components.MainFragmentComponent;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainFragment extends BaseFragment<
        MainFragmentComponent,
        MainFragmentView,
        MainFragmentPresenter,
        MainFragmentViewState>
        implements MainFragmentView {

    @BindString(R.string.hello_firebase) String helloFirebase;
    @BindView(R.id.textView) TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainFragmentComponent constructComponent() {
        return ComponentFactory.getMainFragmentComponent();
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        getPresenter().getHelloFirebase(textView.getText().toString(), helloFirebase);
    }

    @Override
    public void displayHelloFirebase(String helloFirebase) {
        Timber.i("Displaying hello firebase: %s", helloFirebase);
        getViewState().saveHelloFirebase(helloFirebase);
        textView.setText(helloFirebase);
    }

}
