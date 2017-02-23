package com.andrey.nby.di.component;

import com.andrey.nby.App;
import com.andrey.nby.data.DataManager;
import com.andrey.nby.data.prefs.PreferencesHelperImp;
import com.andrey.nby.data.repositories.DatabaseHelperImpl;
import com.andrey.nby.di.module.ApplicationModule;
import com.andrey.nby.ui.currencyListAdapter.CurrencyAdapter;
import com.andrey.nby.ui.currencyListAdapter.CurrencyPresenter;
import com.andrey.nby.ui.mainScreen.MainActivity;
import com.andrey.nby.ui.mainScreen.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
    void inject(DataManager dataManager);
    void inject(DatabaseHelperImpl databaseHelper);
    void inject(PreferencesHelperImp preferencesHelperImp);

    void inject(MainScreenPresenter mainPresenter);
    void inject(MainActivity mainActivity);

    void inject(CurrencyPresenter currencyPresenter);
    void inject(CurrencyAdapter currencyAdapter);
}
