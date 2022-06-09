package com.rpl.reseppedia.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.rpl.reseppedia.source.local.entity.CookingEntity;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;
import com.rpl.reseppedia.source.local.room.RecipeDAO;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final RecipeDAO mRecipeDao;

    private LocalDataSource(RecipeDAO mRecipeDao) {
        this.mRecipeDao = mRecipeDao;
    }

    public static LocalDataSource getInstance(RecipeDAO recipeDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(recipeDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, RecipeEntity> getAllRecipe() {
        return mRecipeDao.getAllRecipe();
    }

    public void insertRecipe(ArrayList<RecipeEntity> recipe) {
        mRecipeDao.insertRecipe(recipe);
    }

    public LiveData<RecipeEntity> getRecipeById(final String recipeId) {
        return mRecipeDao.getRecipeById(recipeId);
    }

    public DataSource.Factory<Integer, WishlistRecipeEntity> getAllWhishlist() {
        return mRecipeDao.getAllWishlist();
    }

    public void insertWishlist(ArrayList<WishlistRecipeEntity> recipe) {
        mRecipeDao.insertWishlist(recipe);
    }

    public LiveData<WishlistRecipeEntity> getWishlistById(final String recipeId) {
        return mRecipeDao.getWishlistById(recipeId);
    }

    public void insertCook(ArrayList<CookingEntity> recipe) {
        mRecipeDao.insertCook(recipe);
    }

    public LiveData<CookingEntity> getCook(final String recipeId) {
        return mRecipeDao.getCook(recipeId);
    }

}
