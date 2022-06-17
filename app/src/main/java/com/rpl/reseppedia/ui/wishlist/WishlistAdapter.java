package com.rpl.reseppedia.ui.wishlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rpl.reseppedia.R;
import com.rpl.reseppedia.databinding.RecipeRowBinding;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;
import com.rpl.reseppedia.ui.detail.DetailRecipeActivity;
import com.squareup.picasso.Picasso;


public class WishlistAdapter extends PagedListAdapter<WishlistRecipeEntity, WishlistAdapter.RecipeViewHolder> {

    WishlistAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<WishlistRecipeEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WishlistRecipeEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull WishlistRecipeEntity oldItem, @NonNull WishlistRecipeEntity newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull WishlistRecipeEntity oldItem, @NonNull WishlistRecipeEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecipeRowBinding binding = RecipeRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        WishlistRecipeEntity recipe = getItem(position);
        if (recipe!=null) {
            holder.bind(recipe);
        }
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        final RecipeRowBinding binding;

        public RecipeViewHolder(RecipeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WishlistRecipeEntity recipe) {

            binding.tvRecipeName.setText(recipe.getNama());
            Picasso.get().load(recipe.getFoto()).placeholder(R.drawable.placeholder_img).error(R.drawable.ic_error).fit().into(binding.ivRecipe);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailRecipeActivity.class);
                intent.putExtra(DetailRecipeActivity.EXTRA_FAV, recipe.getId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
