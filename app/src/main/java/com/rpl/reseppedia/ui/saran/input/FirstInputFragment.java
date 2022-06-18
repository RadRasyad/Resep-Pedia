package com.rpl.reseppedia.ui.saran.input;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.FragmentFirstInputBinding;


public class FirstInputFragment extends Fragment {

    private FragmentFirstInputBinding binding;

    public FirstInputFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondInputFragment siFragment = new SecondInputFragment();
                FragmentManager mFragmentManager = getParentFragmentManager();

                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, siFragment, SecondInputFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}