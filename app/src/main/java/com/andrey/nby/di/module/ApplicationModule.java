package com.andrey.nby.di.module;

import android.content.Context;

import com.andrey.nby.App;
import com.andrey.nby.data.DataManager;
import com.andrey.nby.data.prefs.PreferencesHelperImp;
import com.andrey.nby.data.repositories.DatabaseHelperImpl;
import com.andrey.nby.ui.currencyListAdapter.CurrencyPresenter;
import com.andrey.nby.ui.mainScreen.MainActivity;
import com.andrey.nby.ui.mainScreen.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private App mApplication;

    public ApplicationModule(App mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager(App.getComponent());
    }

    @Provides
    @Singleton
    DatabaseHelperImpl provideDatabaseHelperImpl() {
        return new DatabaseHelperImpl(App.getComponent());
    }

    @Provides
    @Singleton
    MainScreenPresenter provideMainScreenPresenter() {
        return new MainScreenPresenter(App.getComponent());
    }

    @Provides
    @Singleton
    CurrencyPresenter provideCurrencyPresenter(){
        return new CurrencyPresenter(App.getComponent());
    }

    @Provides
    @Singleton
    PreferencesHelperImp providePreferencesHelperImp(){
        return new PreferencesHelperImp(App.getComponent());
    }
}
