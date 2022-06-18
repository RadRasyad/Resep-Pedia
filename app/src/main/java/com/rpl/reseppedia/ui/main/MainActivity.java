package com.rpl.reseppedia.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.ActivityMainBinding;
import com.rpl.reseppedia.utils.SettingPreferences;
import com.rpl.reseppedia.vm.SettingViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    int checkedItem = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_saran, R.id.navigation_wishlist, R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);

        MainViewModel mainViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new SettingViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSettings().observe(this, theme -> {
            if (theme==0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                checkedItem = 0;
            } else if (theme==1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                checkedItem = 1;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                checkedItem = 2;
            }
        });
    }

    public void themeDialog() {
        String[] singleItems = {"Light", "Dark", "Default"};

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);

        MainViewModel mainViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new SettingViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSettings().observe(this, theme -> {
            if (theme==0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                checkedItem = 0;
            } else if (theme==1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                checkedItem = 1;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                checkedItem = 2;
            }
        });

        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        dialogBuilder.setTitle("Tema")
                .setSingleChoiceItems(singleItems, checkedItem, (dialogInterface, i) -> {
                    if (i == 0) {
                        checkedItem = 0;
                    } else if (i == 1) {
                        checkedItem = 1;
                    } else {
                        checkedItem = 2;
                    }
                })
                .setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel())
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    if (checkedItem == 0) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        mainViewModel.saveThemeSetting(0);
                    } else if (checkedItem == 1) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        mainViewModel.saveThemeSetting(1);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        mainViewModel.saveThemeSetting(2);
                    }
                });
        dialogBuilder.create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //home untuk back button
        if (item.getItemId() == R.id.menu_wish) {
            themeDialog();
            return true;
        }
        return true;
    }

}