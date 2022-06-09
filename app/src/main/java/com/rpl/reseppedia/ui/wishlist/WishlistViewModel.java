package com.rpl.reseppedia.ui.wishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;

public class WishlistViewModel extends ViewModel {

    private RecipeRepository recipeRepository;

    public WishlistViewModel(RecipeRepository mRecipeRepository) {
        this.recipeRepository = mRecipeRepository;
    }

    public LiveData<PagedList<WishlistRecipeEntity>> getRecipe() {
        return recipeRepository.getWishlistRecipe();
    }


}