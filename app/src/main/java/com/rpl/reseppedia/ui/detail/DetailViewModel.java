package com.rpl.reseppedia.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.entity.CookingEntity;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {

    private RecipeRepository recipeRepository;

    public DetailViewModel(RecipeRepository mRecipeRepository) {
        this.recipeRepository = mRecipeRepository;
    }

    public LiveData<RecipeEntity> getRecipeById(String id) {
        return recipeRepository.getRecipeById(id);
    }

    public LiveData<WishlistRecipeEntity> getWishById(String id) {
        return recipeRepository.getWishlistById(id);
    }

    public void insertWishlist(WishlistRecipeEntity recipe) {
        recipeRepository.insertWishlist(recipe);
    }

    public void deleteWishlist(String id) {
        recipeRepository.deleteWishlist(id);
    }

    public int checkWish(String recipeId){
        return recipeRepository.checkWish(recipeId);
    }

    public void insertCook(CookingEntity recipe) {
        recipeRepository.insertCook(recipe);
    }

}
