package com.rpl.reseppedia.ui.detail.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.ActivityDetailRecipeBinding;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;
import com.rpl.reseppedia.ui.detail.DetailViewModel;
import com.rpl.reseppedia.ui.detail.IngredientsAdapter;
import com.rpl.reseppedia.vm.ViewModelFactory;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailRecipeActivity extends AppCompatActivity {

    private ActivityDetailRecipeBinding binding;
    private DetailViewModel detailVM;
    private String idMain;
    boolean isSaved;

    private WishlistRecipeEntity recipe;

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_FAV = "extra_fav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplicationContext());
        detailVM =
                new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(DetailViewModel.class);

        //custom appbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(8);

        Bundle extras = getIntent().getExtras();
        String idData = extras.getString(EXTRA_DATA);
        if (idData!=null) {
            getExtraData(idData);
        } else {
            getExtraWish();
        }

    }

    public void getExtraData(String id) {

        detailVM.getRecipeById(id).observe(this, data -> {
            if (data!=null) {
                idMain = id;
                mapRecipe(data);
                checkWishData();
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
            }
        });


    }

    private void mapRecipe(RecipeEntity data) {
        if (data!=null) {
            recipe = new WishlistRecipeEntity(
                    idMain,
                    data.getNama(),
                    data.getPenulis(),
                    data.getDitulis(),
                    data.getWaktu(),
                    data.getPorsi(),
                    data.getKesulitan(),
                    data.getKategori(),
                    data.getDeksripsi(),
                    data.getFoto(),
                    data.getBahan(),
                    data.getCaraMasak()
            );
        }

    }

    public void getExtraWish() {

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            idMain = extras.getString(EXTRA_FAV);
            if (idMain != null) {
                detailVM.getWishById(idMain).observe(this, data -> {
                    if (data!=null) {
                        mapWishlist(data);
                        checkWishData();
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
                    }
                });

            }
        }
    }

    private void mapWishlist(WishlistRecipeEntity data) {
        if (data!=null) {
            recipe = new WishlistRecipeEntity(
                    idMain,
                    data.getNama(),
                    data.getPenulis(),
                    data.getDitulis(),
                    data.getWaktu(),
                    data.getPorsi(),
                    data.getKesulitan(),
                    data.getKategori(),
                    data.getDeksripsi(),
                    data.getFoto(),
                    data.getBahan(),
                    data.getCaraMasak()
            );
        }
    }

    public void checkWishData() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            int count = detailVM.checkWish(idMain);
            handler.post(() -> isSaved = count > 0);
        });

    }

    public void wishData() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            if (isSaved) {
                detailVM.deleteWishlist(idMain);
                Snackbar.make(binding.getRoot(), R.string.hapus_data, Snackbar.LENGTH_SHORT).show();
                isSaved = false;
            } else {
                detailVM.insertWishlist(recipe);
                Snackbar.make(binding.getRoot(), R.string.berhasil_simpan, Snackbar.LENGTH_SHORT).show();
                isSaved = true;
            }
            checkWishData();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (isSaved) {
                        menu.getItem(0).setIcon(R.drawable.ic_bookmark_fill);
                    } else {
                        menu.getItem(0).setIcon(R.drawable.ic_bookmark_outline);
                    }
                }
            });
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //home untuk back button
        if (item.getItemId() == R.id.menu_wish) {
            wishData();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}