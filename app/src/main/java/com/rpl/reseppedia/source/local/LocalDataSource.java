package com.rpl.reseppedia.source.local;

import androidx.paging.DataSource;

import com.rpl.reseppedia.source.local.entity.RecipeEntity;
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

}
