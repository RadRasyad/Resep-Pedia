package com.rpl.reseppedia.ui.detail.recipe;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.ActivityDetailRecipeBinding;
import com.rpl.reseppedia.ui.detail.DetailViewModel;
import com.rpl.reseppedia.ui.detail.IngredientsAdapter;
import com.rpl.reseppedia.ui.home.HomeViewModel;
import com.rpl.reseppedia.vm.ViewModelFactory;
import com.squareup.picasso.Picasso;

public class DetailRecipeActivity extends AppCompatActivity {

    private ActivityDetailRecipeBinding binding;

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_FAV = "extra_fav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //custom appbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(8);

        getExtraData();

    }

    public void getExtraData() {
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplicationContext());
        DetailViewModel detailVM =
                new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(DetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            String id = extras.getString(EXTRA_DATA);
            if (id != null) {
                detailVM.getRecipeById(id).observe(this, data -> {
                    binding.tvTitle.setText(data.getNama());
                    binding.tvAuthor.setText(data.getPenulis());
                    binding.tvRilis.setText(data.getDitulis());
                    binding.tvKesulitan.setText(data.getKesulitan());
                    binding.tvTime.setText(data.getWaktu());
                    binding.tvPorsi.setText(data.getPorsi());
                    binding.tvDesc.setText(data.getDeksripsi());

                    Picasso.get().load(data.getFoto()).placeholder(R.drawable.placeholder_img).error(R.drawable.ic_error).centerCrop().fit().into(binding.ivImg);

                    IngredientsAdapter iAdapter = new IngredientsAdapter(data.getBahan());
                    binding.rvBahan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.rvBahan.setHasFixedSize(true);
                    binding.rvBahan.setAdapter(iAdapter);
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //home untuk back button
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}