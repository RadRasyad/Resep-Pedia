package com.rpl.reseppedia.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.FragmentHomeBinding;
import com.rpl.reseppedia.ui.home.search.SearchFragment;
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

            binding.cariResep.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String newText) {
                    SearchFragment searchFragment = new SearchFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString(SearchFragment.EXTRA_SEARCH, newText);

                    searchFragment.setArguments(bundle);

                    FragmentManager mFragmentManager = getParentFragmentManager();
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, searchFragment, SearchFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.trim() != null && newText.trim().length() != 0) {
                        return true;
                    } else {
                        getAllRecipe();
                        return false;
                    }
                }
            });
        }
    }

    private void sortData() {
        binding.chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            String title = String.valueOf(chip.getText());
            if (title != null) {
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
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        recipeAdapter.submitList(recipe.data);
                        break;
                    case ERROR:
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

        homeVM.getRecipebyCategories(kategori).observe(requireActivity(), recipe -> {
            if (recipe != null) {
                switch (recipe.status) {
                    case LOADING:
                        try {
                            binding.progressBar.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SUCCESS:
                        if (!recipe.data.isEmpty()) {
                            try {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.rvRecipe.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            recipeAdapter.submitList(recipe.data);
                        } else {
                            try {
                                binding.progressBar.setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case ERROR:
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRecipe.setHasFixedSize(true);
        binding.rvRecipe.setAdapter(recipeAdapter);
    }

    private void searchRecipe(String name) {
        homeVM.getRecipebyName(name).observe( requireActivity(), recipe ->{
            if (recipe != null) {
                switch (recipe.status) {
                    case LOADING:
                        try {
                            binding.progressBar.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SUCCESS:
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (!recipe.data.isEmpty()) {
                            binding.rvRecipe.setVisibility(View.VISIBLE);
                            recipeAdapter.submitList(recipe.data);
                        } else {
                            try {
                                binding.progressBar.setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case ERROR:
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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