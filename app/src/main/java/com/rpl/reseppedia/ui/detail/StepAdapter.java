package com.rpl.reseppedia.ui.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rpl.reseppedia.databinding.RowBahanBinding;
import com.rpl.reseppedia.databinding.RowLangkahBinding;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.BahanViewHolder> {

    private final List<String> langkah;

    public StepAdapter(ArrayList<String> langkah) {
        this.langkah = langkah;
    }

    @NonNull
    @Override
    public StepAdapter.BahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLangkahBinding binding = RowLangkahBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BahanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.BahanViewHolder holder, int position) {
        String dLangkah = langkah.get(position);

        holder.langkah.setText(dLangkah);
        int index = holder.getBindingAdapterPosition();
        holder.no.setText(String.valueOf(index+1));

    }

    @Override
    public int getItemCount() {
        return langkah.size();
    }

    public static class BahanViewHolder extends RecyclerView.ViewHolder {

        TextView langkah, no;

        public BahanViewHolder(RowLangkahBinding binding) {
            super(binding.getRoot());

            langkah = binding.tvLangkah;
            no = binding.number;

        }
    }
}
