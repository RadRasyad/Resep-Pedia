package com.rpl.reseppedia.ui.home.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.FragmentSearchBinding;
import com.rpl.reseppedia.ui.home.HomeViewModel;
import com.rpl.reseppedia.ui.home.RecipeAdapter;
import com.rpl.reseppedia.vm.ViewModelFactory;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private HomeViewModel homeVM;
    private RecipeAdapter recipeAdapter;
    public static final String EXTRA_SEARCH = "extra_search";
    String search;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
        homeVM = new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(HomeViewModel.class);
        recipeAdapter = new RecipeAdapter();

        if (getArguments()!=null) {
            search = getArguments().getString(EXTRA_SEARCH);
            binding.cariResep.setQuery(search, false);
        }

        searchRecipe(search);

        binding.cariResep.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {

                searchRecipe(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim() != null && newText.trim().length() != 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    private void searchRecipe(String name) {
        homeVM.getRecipebyName(name).observe(requireActivity(), recipe -> {
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
                            try {
                                binding.rvRecipe.setVisibility(View.VISIBLE);
                                binding.emptyState.getRoot().setVisibility(View.GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            recipeAdapter.submitList(recipe.data);
                        } else {
                            try {
                                binding.rvRecipe.setVisibility(View.GONE);
                                binding.progressBar.setVisibility(View.GONE);
                                final Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.emptyState.getRoot().setVisibility(View.VISIBLE);
                                    }
                                }, 1000);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case ERROR:
                        try {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.emptyState.getRoot().setVisibility(View.VISIBLE);
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
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}

