package com.rpl.reseppedia.ui.saran.input;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

        binding.inputNamaResep.addTextChangedListener(inputTextWatcher);
        binding.inputNamaPenulis.addTextChangedListener(inputTextWatcher);
        binding.inputWaktu.addTextChangedListener(inputTextWatcher);
        binding.inputPorsi.addTextChangedListener(inputTextWatcher);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle mBundle = new Bundle();
                mBundle.putString(SecondInputFragment.EXTRA_NAMARESEP, binding.inputNamaResep.getText().toString());
                String penulis, waktu, porsi;
                penulis = binding.inputNamaPenulis.getText().toString();
                waktu = binding.inputWaktu.getText().toString();
                porsi = binding.inputPorsi.getText().toString();

                SecondInputFragment siFragment = new SecondInputFragment();
                siFragment.setArguments(mBundle);
                siFragment.setPenulis(penulis);
                siFragment.setPorsi(porsi);
                siFragment.setWaktu(waktu);

                FragmentManager mFragmentManager = getParentFragmentManager();

                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, siFragment, SecondInputFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String resep, penulis, waktu, porsi;
            resep = binding.inputNamaResep.getText().toString().trim();
            penulis = binding.inputNamaPenulis.getText().toString().trim();
            waktu = binding.inputWaktu.getText().toString().trim();
            porsi = binding.inputPorsi.getText().toString().trim();

            boolean state;
            if (!resep.isEmpty() && !penulis.isEmpty() && !waktu.isEmpty() && !porsi.isEmpty()) {
                state = true;
            } else {
                state = false;
            }
            binding.btnNext.setEnabled(state);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}