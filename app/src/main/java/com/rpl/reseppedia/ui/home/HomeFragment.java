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

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sortData();
        if (getActivity()!=null) {

            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            HomeViewModel homeVM =
                    new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(HomeViewModel.class);


            RecipeAdapter recipeAdapter = new RecipeAdapter();
            homeVM.getRecipe().observe( requireActivity(), recipe -> {
                if (recipe != null) {
                    switch (recipe.status) {
                        case LOADING:
                            //binding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            //binding.progressBar.setVisibility(View.GONE);
                            recipeAdapter.submitList(recipe.data);
                            break;
                        case ERROR:
                            //binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvRecipe.setHasFixedSize(true);
            binding.rvRecipe.setAdapter(recipeAdapter);

        }
    }


    private void sortData() {
        String sort;

        binding.chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip!=null) {
                Log.d("Chip", chip.getText().toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}