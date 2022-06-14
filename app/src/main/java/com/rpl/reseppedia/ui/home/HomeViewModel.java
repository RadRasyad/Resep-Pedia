package com.rpl.reseppedia.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;


import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.vo.Resource;

public class HomeViewModel extends ViewModel {

    private RecipeRepository recipeRepository;

    public HomeViewModel(RecipeRepository mRecipeRepository) {
        this.recipeRepository = mRecipeRepository;
    }

    public LiveData<Resource<PagedList<RecipeEntity>>> getRecipe() {
        return recipeRepository.getAllRecipe();
    }

    public LiveData<Resource<PagedList<RecipeEntity>>> getRecipebyCategories(String kategori) {
        return recipeRepository.getAllRecipeByCategories(kategori);
    }

}