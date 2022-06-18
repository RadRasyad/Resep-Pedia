package com.rpl.reseppedia.ui.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rpl.reseppedia.databinding.RowBahanBinding;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.BahanViewHolder> {

    private final List<String> bahan;

    public IngredientsAdapter(ArrayList<String> bahan) {
        this.bahan = bahan;
    }

    @NonNull
    @Override
    public IngredientsAdapter.BahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowBahanBinding binding = RowBahanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BahanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.BahanViewHolder holder, int position) {
        String dBahan = bahan.get(position);

        holder.bahan.setText(dBahan);

    }

    @Override
    public int getItemCount() {
        return bahan.size();
    }

    public static class BahanViewHolder extends RecyclerView.ViewHolder {

        TextView bahan;

        public BahanViewHolder(RowBahanBinding binding) {
            super(binding.getRoot());

            bahan = binding.tvBahan;


        }
    }
}
