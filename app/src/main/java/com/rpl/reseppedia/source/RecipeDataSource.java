package com.rpl.reseppedia.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.vo.Resource;

public interface RecipeDataSource {

    LiveData<Resource<PagedList<RecipeEntity>>> getAllRecipe();
}
