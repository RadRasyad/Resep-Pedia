package com.rpl.reseppedia.ui.cook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rpl.reseppedia.databinding.FragmentCookBinding;

public class CookFragment extends Fragment {

    private FragmentCookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CookViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CookViewModel.class);

        binding = FragmentCookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}