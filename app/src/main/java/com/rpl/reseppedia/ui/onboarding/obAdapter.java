package com.rpl.reseppedia.ui.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rpl.reseppedia.databinding.OnBoardingRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class obAdapter extends RecyclerView.Adapter<obAdapter.OnBoardingViewHolder> {

    private final List<obEntity> listObi;

    public obAdapter(ArrayList<obEntity> listBoarding) {
        this.listObi = listBoarding;
    }

    @NonNull
    @Override
    public obAdapter.OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OnBoardingRowBinding binding = OnBoardingRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OnBoardingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull obAdapter.OnBoardingViewHolder holder, int position) {

        obEntity obi = listObi.get(position);

        holder.tvTitle.setText(obi.getTitle());
        Picasso.get().load(obi.getIllustration()).into(holder.illustration);
    }

    @Override
    public int getItemCount() {
        return listObi.size();
    }

    public static class OnBoardingViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView illustration;

        public OnBoardingViewHolder(@NonNull OnBoardingRowBinding binding) {
            super(binding.getRoot());
            tvTitle = binding.tvTitle;
            illustration = binding.ivIllustration;
        }

    }
}
