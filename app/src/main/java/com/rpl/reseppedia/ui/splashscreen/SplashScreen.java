package com.rpl.reseppedia.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rpl.reseppedia.databinding.ActivitySplashScreenBinding;
import com.rpl.reseppedia.ui.main.MainActivity;
import com.rpl.reseppedia.ui.main.MainViewModel;
import com.rpl.reseppedia.ui.onboarding.on_board;
import com.rpl.reseppedia.utils.SettingPreferences;
import com.rpl.reseppedia.vm.SettingViewModelFactory;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);
        MainViewModel mainViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new SettingViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSettings().observe(this, theme -> {
            if (theme==0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (theme==1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });

        int time = 3000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, on_board.class);
            startActivity(intent);
            finish();
        }, time);

    }

}