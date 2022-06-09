package com.rpl.reseppedia.ui.wishlist;

import android.os.Bundle;
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

import com.rpl.reseppedia.databinding.FragmentWishlistBinding;
import com.rpl.reseppedia.ui.home.HomeViewModel;
import com.rpl.reseppedia.ui.home.RecipeAdapter;
import com.rpl.reseppedia.vm.ViewModelFactory;

public class WishlistFragment extends Fragment {

    private FragmentWishlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity()!=null) {

            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            WishlistViewModel homeVM =
                    new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(WishlistViewModel.class);


            WishlistAdapter recipeAdapter = new WishlistAdapter();
            homeVM.getRecipe().observe( requireActivity(), recipe -> {
                if (recipe != null) {
                    recipeAdapter.submitList(recipe);
                } else {
                    binding.rvRecipe.setVisibility(View.GONE);
                }
            });

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