package com.rpl.reseppedia.ui.detail.recipe;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.ActivityDetailRecipeBinding;

public class DetailRecipeActivity extends AppCompatActivity {

    private ActivityDetailRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //custom appbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("");


    }
}