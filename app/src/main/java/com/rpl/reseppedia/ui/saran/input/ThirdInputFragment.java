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

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.FragmentThirdInputBinding;


public class ThirdInputFragment extends Fragment {

    private FragmentThirdInputBinding binding;
    public static final String EXTRA_NAMARESEP = "extra_namaresep";
    public static final String EXTRA_PENULIS = "extra_penulis";
    public static final String EXTRA_WAKTU = "extra_waktu";
    public static final String EXTRA_PORSI = "extra_porsi";
    public static final String EXTRA_DESK = "extra_deskripsi";
    public String penulis;
    public String porsi;
    public String waktu;
    public String deskripsi;

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPorsi() {
        return porsi;
    }

    public void setPorsi(String porsi) {
        this.porsi = porsi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ThirdInputFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            String penulis = savedInstanceState.getString(EXTRA_PENULIS);
            String porsi = savedInstanceState.getString(EXTRA_PORSI);
            String waktu = savedInstanceState.getString(EXTRA_WAKTU);
            String desk = savedInstanceState.getString(EXTRA_DESK);
            setPenulis(penulis);
            setPorsi(porsi);
            setWaktu(waktu);
            setDeskripsi(desk);
        }


        if (getArguments() != null) {
            String namaResep = getArguments().getString(EXTRA_NAMARESEP);

            binding.inputBahan.addTextChangedListener(inputTextWatcher);
            binding.btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FourthInputFragment fiFragment = new FourthInputFragment();
                    Bundle mBundle = new Bundle();
                    mBundle.putString(FourthInputFragment.EXTRA_NAMARESEP, namaResep);
                    String penulis, waktu, porsi, deskripsi, bahan;
                    penulis = getPenulis();
                    waktu = getWaktu();
                    porsi = getPorsi();
                    deskripsi = getDeskripsi();
                    bahan = binding.inputBahan.getText().toString();

                    fiFragment.setArguments(mBundle);
                    fiFragment.setPenulis(penulis);
                    fiFragment.setPorsi(porsi);
                    fiFragment.setWaktu(waktu);
                    fiFragment.setDeskripsi(deskripsi);
                    fiFragment.setBahan(bahan);

                    FragmentManager mFragmentManager = getParentFragmentManager();

                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, fiFragment, FourthInputFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String deskripsi;
            deskripsi = binding.inputBahan.getText().toString().trim();

            boolean state;
            if (!deskripsi.isEmpty()) {
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EXTRA_PENULIS, getPenulis());
        outState.putString(EXTRA_PORSI, getPorsi());
        outState.putString(EXTRA_WAKTU, getWaktu());
        outState.putString(EXTRA_DESK, getDeskripsi());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}