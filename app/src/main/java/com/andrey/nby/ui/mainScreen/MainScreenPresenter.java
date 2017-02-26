package com.andrey.nby.ui.mainScreen;

import android.support.annotation.NonNull;

import com.andrey.nby.data.DataManager;
import com.andrey.nby.di.component.ApplicationComponent;

import javax.inject.Inject;

public class MainScreenPresenter {

    @Inject
    DataManager mDataManager;

    private static final String TAG = "MainScreenPresenter";

    public MainScreenPresenter(@NonNull ApplicationComponent appComponent) {
        appComponent.inject(this);
    }

    public void endLoadingAnimation() {
    }

    public void startLoadingAnimation() {

    }
}
