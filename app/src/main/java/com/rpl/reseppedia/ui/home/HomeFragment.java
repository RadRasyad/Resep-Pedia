package com.rpl.reseppedia.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.rpl.reseppedia.databinding.FragmentHomeBinding;
import com.rpl.reseppedia.vm.ViewModelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeVM;
    private RecipeAdapter recipeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllRecipe();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity()!=null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            homeVM = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(HomeViewModel.class);
            recipeAdapter = new RecipeAdapter();
            getAllRecipe();
            sortData();
        }
    }

    private void sortData() {
        binding.chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            String title = String.valueOf(chip.getText());
            if (title!=null) {
                switch (title) {
                    case "Semua":
                        getAllRecipe();
                        break;
                    case "Makanan Ringan":
                        getRecipeByCategories("makanan ringan");
                        break;
                    case "Makanan Berat":
                        getRecipeByCategories("makanan berat");
                        break;
                }
                Log.d("Chip", chip.getText().toString());
            }
        });
    }

    private void getAllRecipe() {
        homeVM.getRecipe().observe( requireActivity(), recipe -> {
            if (recipe != null) {
                switch (recipe.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);

                        recipeAdapter.submitList(recipe.data);
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecipe.setHasFixedSize(true);
        binding.rvRecipe.setAdapter(recipeAdapter);
    }

    private void getRecipeByCategories(String kategori) {

        homeVM.getRecipebyCategories(kategori).observe( requireActivity(), recipe -> {
            if (recipe != null) {
                switch (recipe.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        if (!recipe.data.isEmpty()) {
                            binding.rvRecipe.setVisibility(View.VISIBLE);
                            recipeAdapter.submitList(recipe.data);
                        } else {
                            binding.rvRecipe.setVisibility(View.GONE);
                        }
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecipe.setHasFixedSize(true);
        binding.rvRecipe.setAdapter(recipeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}