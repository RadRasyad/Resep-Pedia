package com.rpl.reseppedia.ui.saran;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rpl.reseppedia.R;
import com.rpl.reseppedia.ui.saran.input.FirstInputFragment;
import com.rpl.reseppedia.vo.Resource;

import java.util.Objects;

public class SaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FirstInputFragment fiFragment = new FirstInputFragment();
        Fragment fragment = mFragmentManager.findFragmentByTag(FirstInputFragment.class.getSimpleName());

        if (!(fragment instanceof FirstInputFragment)) {
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, fiFragment, FirstInputFragment.class.getSimpleName())
                    .commit();
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Saran Resep");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /*
    private void dialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);

        dialogBuilder.setCancelable(false);
        dialogBuilder.setTitle("Batal Mengisi Saran?")
                .setPositiveButton(getResources().getString(R.string.ya), ((dialogInterface, i) -> {
                    finish();
                }))
                .setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel());
        dialogBuilder.create().show();
    }


    @Override
    public void onBackPressed() {
        dialog();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}