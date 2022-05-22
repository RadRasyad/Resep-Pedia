package com.rpl.reseppedia.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.RecipeRowBinding;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.RecipeViewHolder>{

    private ArrayList<RecipeResponse> listRecipe = new ArrayList<>();

    public void setRecipe(ArrayList<RecipeResponse> listRecipe) {
        if (listRecipe!=null) {
            this.listRecipe.clear();
            this.listRecipe = listRecipe;
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecipeRowBinding binding = RecipeRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        RecipeResponse recipe = listRecipe.get(position);

        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final RecipeRowBinding binding;

        public RecipeViewHolder(RecipeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RecipeResponse recipe) {
            Log.d("nama resep : ", String.valueOf(recipe.getBahan()));
            binding.tvRecipeName.setText(recipe.getNama());
            Picasso.get().load(recipe.getFoto()).placeholder(R.drawable.placeholder_img).error(R.drawable.ic_error).fit().into(binding.ivRecipe);
        }
    }
}