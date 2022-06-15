package com.rpl.reseppedia.ui.wishlist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.EmptyStateBookmarkBinding;
import com.rpl.reseppedia.databinding.FragmentWishlistBinding;
import com.rpl.reseppedia.vm.ViewModelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WishlistFragment extends Fragment {

    private FragmentWishlistBinding binding;
    private EmptyStateBookmarkBinding emptyStateBookmarkBinding;
    private Boolean isEmpty;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        emptyStateBookmarkBinding = EmptyStateBookmarkBinding.bind(view);
        return view;
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
                 {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.getMainLooper());
                    executor.execute(() -> {
                        if (recipe != null && !recipe.isEmpty()) {
                            recipeAdapter.submitList(recipe);
                            isEmpty = false;
                        } else {
                            isEmpty = true;
                        }
                        handler.post(() -> {
                            if (isEmpty) {
                                try {
                                    binding.emptyState.getRoot().setVisibility(View.VISIBLE);
                                    binding.rvRecipe.setVisibility(View.GONE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    binding.emptyState.getRoot().setVisibility(View.GONE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    });

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