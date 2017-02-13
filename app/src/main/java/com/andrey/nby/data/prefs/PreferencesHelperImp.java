package com.andrey.nby.data.prefs;

import com.andrey.nby.di.component.ApplicationComponent;

public class PreferencesHelperImp implements PreferencesHelper {

    public PreferencesHelperImp(ApplicationComponent component) {
        component.inject(this);
    }
}
