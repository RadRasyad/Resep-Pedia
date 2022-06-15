package com.rpl.reseppedia.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rpl.reseppedia.ui.main.MainViewModel;
import com.rpl.reseppedia.utils.SettingPreferences;

public class SettingViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private SettingPreferences pref;

    public SettingViewModelFactory(SettingPreferences pref) {
        this.pref = pref;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(pref);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
