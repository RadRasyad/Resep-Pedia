package com.rpl.reseppedia.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.rpl.reseppedia.utils.SettingPreferences;

public class MainViewModel extends ViewModel {
    private final SettingPreferences pref;

    public MainViewModel(SettingPreferences pref) {
        this.pref = pref;
    }

    public LiveData<Integer> getThemeSettings() {
        return LiveDataReactiveStreams.fromPublisher(pref.getThemeSetting());
    }

    public void saveThemeSetting(Integer theme) {
        pref.saveThemeSetting(theme);
    }
}
