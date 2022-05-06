package com.rpl.reseppedia.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rpl.reseppedia.databinding.ActivitySplashScreenBinding;
import com.rpl.reseppedia.ui.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int time = 3000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, time);

    }

}