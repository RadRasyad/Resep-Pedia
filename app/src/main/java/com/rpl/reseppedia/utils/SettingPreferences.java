package com.rpl.reseppedia.utils;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxDataStore;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class SettingPreferences {

    private final Preferences.Key<Integer> THEME_KEY = PreferencesKeys.intKey("theme_setting");
    private final RxDataStore<Preferences> dataStore;

    private SettingPreferences(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    private static volatile SettingPreferences INSTANCE;

    public static SettingPreferences getInstance(final RxDataStore<Preferences> dataStore) {
        if (INSTANCE == null) {
            synchronized (SettingPreferences.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SettingPreferences(dataStore);
                }
            }
        }
        return INSTANCE;
    }

    public Flowable<Integer> getThemeSetting() {
        return dataStore.data().map(preferences -> {
                    if (preferences.get(THEME_KEY) != null) {
                        return preferences.get(THEME_KEY);
                    } else {
                        return 0;
                    }
                }
        );
    }

    public void saveThemeSetting(int theme) {
        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(THEME_KEY, theme);
            return Single.just(mutablePreferences);
        });
    }

}
