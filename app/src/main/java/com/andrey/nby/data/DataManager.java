package com.andrey.nby.data;

import android.content.Context;

import com.andrey.nby.data.repositories.DatabaseHelperImpl;
import com.andrey.nby.di.component.ApplicationComponent;

import javax.inject.Inject;

public class DataManager {

    @Inject
    DatabaseHelperImpl databaseHelper;

    private static final String TAG = "DataManager";

    public DataManager(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void updateDatabase(Context context) {
        databaseHelper.updateCurrency(context);
    }
}