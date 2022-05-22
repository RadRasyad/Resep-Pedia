package com.rpl.reseppedia.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rpl.reseppedia.databinding.FragmentHomeBinding;
import com.rpl.reseppedia.vm.ViewModelFactory;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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



            /*
            HomeViewModel homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);
            ArrayList<RecipeResponse> recipeList = homeViewModel.getRecipe();

            ResepAdapter resepAdapter = new ResepAdapter();
            if (recipeList != null) {
                Log.d("RV : ", "Data didapatkan");
                resepAdapter.setRecipe(recipeList);
                resepAdapter.notifyDataSetChanged();
            }
            binding.rvRecipe.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvRecipe.setHasFixedSize(true);
            binding.rvRecipe.setAdapter(resepAdapter);

             */


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}