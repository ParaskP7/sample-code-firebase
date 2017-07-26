package com.hubrickchallenge.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.actions.NotificationActions;
import com.hubrickchallenge.android.actions.SnackbarActions;
import com.hubrickchallenge.android.tools.dagger.components.BaseFragmentComponent;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseFragment<
        COMPONENT extends BaseFragmentComponent,
        VIEW extends MvpView,
        PRESENTER extends MvpPresenter<VIEW>,
        VIEW_STATE extends ViewState<VIEW>>
        extends Fragment implements MvpViewStateDelegateCallback<VIEW, PRESENTER, VIEW_STATE> {

    @Inject App application;

    /**
     * Can't inject directly, as the presenter instantiation needs to happen by mosby in {@link this#createPresenter()}.
     */
    @Inject Provider<PRESENTER> presenterProvider;
    private PRESENTER presenter;

    /**
     * Can't inject directly, as the presenter instantiation needs to happen by mosby in {@link this#createViewState()}.
     */
    @Inject Provider<VIEW_STATE> viewStateProvider;
    private VIEW_STATE viewState;

    /**
     * Instead of extending {@link MvpFragment} or {@link MvpViewStateFragment} mosby's delegate is being used. To do that certain activity
     * lifecycle methods need to be propagated to the delegate.
     */
    @Nullable protected FragmentMvpDelegate mvpDelegate;

    private boolean restoringViewState;

    // LIFECYCLE // ************************************************************************************************************************

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getMvpDelegate().onAttach(getActivity());
        Timber.d("%s attached.", getClass().getSimpleName());
    }

    private FragmentMvpDelegate getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this, this, true, true);
        }
        return mvpDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
        Timber.d("%s created.", getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        setDagger();
        setButterKnife(view);
        Timber.d("%s create view.", getClass().getSimpleName());
        return view;
    }

    @SuppressWarnings("unchecked")
    private void setDagger() {
        COMPONENT component = constructComponent();
        component.inject(this);
    }

    protected abstract int getLayoutId();

    private void setButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
        Timber.d("%s view created.", getClass().getSimpleName());
    }

    protected abstract COMPONENT constructComponent();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
        Timber.d("%s activity created.", getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
        Timber.d("%s started.", getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
        Timber.d("%s resumed.", getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        Timber.d("%s paused.", getClass().getSimpleName());
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Timber.d("%s instance state saved.", getClass().getSimpleName());
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        Timber.d("%s stopped.", getClass().getSimpleName());
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onDestroyView() {
        Timber.d("%s view destroyed.", getClass().getSimpleName());
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        Timber.d("%s destroyed.", getClass().getSimpleName());
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onDetach() {
        Timber.d("%s detached.", getClass().getSimpleName());
        super.onDetach();
        getMvpDelegate().onDetach();
    }

    // MVP // ******************************************************************************************************************************

    @NonNull
    @Override
    public PRESENTER createPresenter() {
        Timber.d("%s presenter created.", getClass().getSimpleName());
        return presenterProvider.get();
    }

    @Override
    public void setPresenter(PRESENTER presenter) {
        Timber.d("%s set presenter.", getClass().getSimpleName());
        this.presenter = presenter;
    }

    @Override
    public PRESENTER getPresenter() {
        Timber.d("%s get presenter.", getClass().getSimpleName());
        return presenter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public VIEW getMvpView() {
        Timber.d("%s get mvp view.", getClass().getSimpleName());
        return (VIEW) this;
    }

    // VIEW STATE // ***********************************************************************************************************************

    @NonNull
    @Override
    public VIEW_STATE createViewState() {
        Timber.d("%s view state created.", getClass().getSimpleName());
        return viewStateProvider.get();
    }

    @Override
    public void setViewState(VIEW_STATE viewState) {
        Timber.d("%s set view state.", getClass().getSimpleName());
        this.viewState = viewState;
    }

    @Override
    public void onNewViewStateInstance() {
        Timber.d("%s new view state instance.", getClass().getSimpleName());
        onFirstCreate();
    }

    /**
     * Default implementation not doing anything. Override when required to perform long running tasks only once, then save their state in
     * the {@link VIEW_STATE}.
     * <p>
     * NOTE: Default implementation not doing anything. Override when required.
     */
    protected void onFirstCreate() {
        Timber.d("Initializing fragment for the first time (view state is currently empty).");
    }

    @Override
    public VIEW_STATE getViewState() {
        Timber.d("%s get view state.", getClass().getSimpleName());
        return viewState;
    }

    @Override
    public void setRestoringViewState(boolean restoringViewState) {
        Timber.d("%s set restoring view state [%b].", getClass().getSimpleName(), restoringViewState);
        this.restoringViewState = restoringViewState;
    }

    @Override
    public boolean isRestoringViewState() {
        Timber.d("%s is restoring view state.", getClass().getSimpleName());
        return restoringViewState;
    }

    /**
     * Called right after the state of the view has been restored from the {@link VIEW_STATE}.
     * <p>
     * NOTE: Default implementation not doing anything. Override when required.
     */
    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        Timber.d("%s view state restored [%b].", getClass().getSimpleName(), instanceStateRetained);
    }

    // ACTIONS // **************************************************************************************************************************

    protected NotificationActions notification() {
        return application.notification();
    }

    protected SnackbarActions snackbar() {
        return application.snackbar();
    }

}
