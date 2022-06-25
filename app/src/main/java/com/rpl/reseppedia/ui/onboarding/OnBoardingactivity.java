package com.rpl.reseppedia.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.ActivityOnBoardBinding;
import com.rpl.reseppedia.ui.main.MainActivity;

import java.util.ArrayList;

public class OnBoardingactivity extends AppCompatActivity {

    private ActivityOnBoardBinding binding;
    private final ArrayList<obEntity> listBoarding = new ArrayList<>();
    private obAdapter adapter;
    private ViewPager2 viewPager;
    SharedPreferences firstTimeState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFirstTimeState();

        listBoarding.addAll(getList());

        adapter = new obAdapter(listBoarding);

        viewPager = binding.vp2Onboard;
        viewPager.setAdapter(adapter);

        //memanggil fungsi
        initIndicatorSlider();
        setCurIndicator(0);
        setNextButton(0);
        setGetStarted(0);

        //fungsi untuk ambil posisi indicator
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurIndicator(position);
                setNextButton(position);
                setGetStarted(position);
            }
        });

        //fungsi intent pd button next
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jika posisi viewpager belum maksimal maka next
                if (viewPager.getCurrentItem()+1 < listBoarding.size()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            }
        });

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = firstTimeState.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();

                Intent intent = new Intent(OnBoardingactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void setFirstTimeState() {
        firstTimeState = getSharedPreferences("firstTimeState", MODE_PRIVATE);
        boolean firstime = firstTimeState.getBoolean("firstTime", true);

        if (!firstime) {
            Intent intent = new Intent(OnBoardingactivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    //fungsi untuk mengambil data dari value->string
    public ArrayList<obEntity> getList() {
        String[] dTitle = getResources().getStringArray(R.array.Title);
        TypedArray dIllustration = getResources().obtainTypedArray(R.array.illustration);

        ArrayList<obEntity> list = new ArrayList<>();
        for (int i = 0; i<dTitle.length; i++) {
            obEntity listObi = new obEntity();
            listObi.setTitle(dTitle[i]);
            listObi.setIllustration(dIllustration.getResourceId(i, -1));

            list.add(listObi);
        }
        return list;
    }

    //fungsi untuk set total indicator slider
    private void initIndicatorSlider() {
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i=0; i<indicator.length; i++) {
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_board_indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            binding.lytObrdIndctr.addView(indicator[i]);
        }
    }

    //fungsi untuk set view indicator active atau inactive(visibility)
    private void setCurIndicator(int index) {
        int count = binding.lytObrdIndctr.getChildCount();
        for (int i=0; i<count; i++) {
            ImageView iv = (ImageView) binding.lytObrdIndctr.getChildAt(i);

            if (i == index) {
                iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_board_indicator_active));
            }
            else {
                iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_board_indicator_inactive));
            }
        }
    }

    //fungsi set visibility btn next
    private void setNextButton(int index) {
        Button next = binding.btnNext;
        if (index==2) {
            next.setVisibility(View.GONE);
        }
        else {
            next.setVisibility(View.VISIBLE);
        }
    }

    //fungsi set visibility btn get started
    private void setGetStarted(int index) {
        Button getStarted = binding.btnStart;
        if (index==2) {
            getStarted.setVisibility(View.VISIBLE);
        }
        else {
            getStarted.setVisibility(View.GONE);
        }
    }
}