package com.andrey.nby;

import android.app.Application;

import com.andrey.nby.di.component.ApplicationComponent;
import com.andrey.nby.di.component.DaggerApplicationComponent;
import com.andrey.nby.di.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfig();
        mComponent = buildComponent();
    }

    private void initRealmConfig() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private ApplicationComponent buildComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getComponent() {
        return mComponent;
    }
}

