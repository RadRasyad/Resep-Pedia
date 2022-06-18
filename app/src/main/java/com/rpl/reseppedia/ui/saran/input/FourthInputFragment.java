package com.rpl.reseppedia.ui.saran.input;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.FragmentFourthInputBinding;
import com.rpl.reseppedia.source.remote.response.SaranResponse;

import java.util.HashMap;
import java.util.Map;


public class FourthInputFragment extends Fragment {

    private FragmentFourthInputBinding binding;
    public static final String EXTRA_NAMARESEP = "extra_namaresep";
    public static final String EXTRA_PENULIS = "extra_penulis";
    public static final String EXTRA_WAKTU = "extra_waktu";
    public static final String EXTRA_PORSI = "extra_porsi";
    public static final String EXTRA_DESK = "extra_deskripsi";
    public static final String EXTRA_BAHAN = "extra_bahan";
    public String namaResep;
    public String penulis;
    public String porsi;
    public String waktu;
    public String deskripsi;
    public String bahan;

    public String getNamaResep() {
        return namaResep;
    }

    public void setNamaResep(String namaResep) {
        this.namaResep = namaResep;
    }

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

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public FourthInputFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFourthInputBinding.inflate(inflater, container, false);
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
            String bahan = savedInstanceState.getString(EXTRA_BAHAN);
            setPenulis(penulis);
            setPorsi(porsi);
            setWaktu(waktu);
            setDeskripsi(desk);
            setBahan(bahan);
        }

        if (getArguments() != null) {
            binding.inputLangkah.addTextChangedListener(inputTextWatcher);
            String namaResep = getArguments().getString(EXTRA_NAMARESEP);
            setNamaResep(namaResep);



            binding.btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String langkah = binding.inputLangkah.getText().toString();
                    Log.d("caraMasak", langkah);

                    SaranResponse saranResep = new SaranResponse(getNamaResep(), getPenulis(), getWaktu(), getPorsi(), getDeskripsi(), getBahan(), langkah);
                    kirimSaran(saranResep);
                }
            });

        }

    }

    private void kirimSaran(SaranResponse saranResep) {
        Map<String, Object> saran = new HashMap<>();
        saran.put("nama_resep", saranResep.getNama());
        saran.put("penulis", saranResep.getPenulis());
        saran.put("waktu", saranResep.getWaktu());
        saran.put("porsi", saranResep.getPorsi());
        saran.put("deskripsi", saranResep.getDeskripsi());
        saran.put("bahan", saranResep.getBahan());
        saran.put("langkah", saranResep.getCara_masak());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Saranresep")
                .add(saran)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Snackbar.make(binding.getRoot(), "Saran Dikirim", Snackbar.LENGTH_SHORT).show();
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().finish();
                            }
                        }, 2000);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(binding.getRoot(), "Saran Gagal Dikirim", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String deskripsi;
            deskripsi = binding.inputLangkah.getText().toString().trim();

            boolean state;
            if (!deskripsi.isEmpty()) {
                state = true;
            } else {
                state = false;
            }
            binding.btnSend.setEnabled(state);
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
        outState.putString(EXTRA_BAHAN, getBahan());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}