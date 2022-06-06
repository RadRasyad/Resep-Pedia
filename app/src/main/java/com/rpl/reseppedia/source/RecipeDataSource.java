package com.rpl.reseppedia.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;
import com.rpl.reseppedia.vo.Resource;

public interface RecipeDataSource {

    LiveData<Resource<PagedList<RecipeEntity>>> getAllRecipe();

    LiveData<RecipeEntity> getRecipeById(final String recipeId);

    LiveData<PagedList<WishlistRecipeEntity>> getWishlistRecipe();

    LiveData<WishlistRecipeEntity> getWishlistById(final String recipeId);
}
