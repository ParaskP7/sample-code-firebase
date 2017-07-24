package com.hubrickchallenge.android.tools.dagger.components;

import com.hubrickchallenge.android.activity.main.MainFragment;
import com.hubrickchallenge.android.tools.dagger.modules.MainFragmentModule;
import com.hubrickchallenge.android.tools.dagger.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {MainFragmentModule.class})
public interface MainFragmentComponent extends BaseFragmentComponent<MainFragment> {

}
