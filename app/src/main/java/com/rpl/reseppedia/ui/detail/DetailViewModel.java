package com.rpl.reseppedia.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;

public class DetailViewModel extends ViewModel {

    private RecipeRepository recipeRepository;

    public DetailViewModel(RecipeRepository mRecipeRepository) {
        this.recipeRepository = mRecipeRepository;
    }

    public LiveData<RecipeEntity> getRecipeById(String id) {
        return recipeRepository.getRecipeById(id);
    }

}
