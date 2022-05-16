package com.rpl.reseppedia.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rpl.reseppedia.databinding.FragmentHomeBinding;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;

import java.util.ArrayList;

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

        if (getActivity()!=null) {

            HomeViewModel homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);

            ArrayList<RecipeResponse> recipeList = homeViewModel.getRecipe();

            RecipeAdapter recipeAdapter = new RecipeAdapter();
            if (recipeList != null) {
                Log.d("RV : ", "Data didapatkan");
                recipeAdapter.setRecipe(recipeList);
                recipeAdapter.notifyDataSetChanged();
            }
            binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvRecipe.setHasFixedSize(true);
            binding.rvRecipe.setAdapter(recipeAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}