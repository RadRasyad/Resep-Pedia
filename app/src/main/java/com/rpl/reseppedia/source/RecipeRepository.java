package com.rpl.reseppedia.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.rpl.reseppedia.source.local.entity.Recipe;
import com.rpl.reseppedia.vo.Resource;

public class RecipeRepository implements RecipeDataSource {

    @Override
    public LiveData<Resource<PagedList<Recipe>>> getAllRecipe() {
        return null;
    }
}
