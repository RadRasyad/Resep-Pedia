package com.rpl.reseppedia.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rpl.reseppedia.source.local.entity.CookingEntity;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;

import java.util.List;


@Dao
public interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    DataSource.Factory<Integer, RecipeEntity> getAllRecipe();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(List<RecipeEntity> recipe);

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    LiveData<RecipeEntity> getRecipeById(String recipeId);

    @Query("SELECT * FROM wishlist")
    DataSource.Factory<Integer, WishlistRecipeEntity> getAllWishlist();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWishlist(WishlistRecipeEntity recipe);

    @Query("DELETE FROM wishlist WHERE id = :recipeId")
    void deleteWishlist(String recipeId);

    @Query("SELECT * FROM wishlist WHERE id = :recipeId")
    LiveData<WishlistRecipeEntity> getWishlistById(String recipeId);

    @Query("SELECT count(*) FROM wishlist WHERE wishlist.id = :recipeId")
    int checkWish(String recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCook(CookingEntity recipe);

    @Query("DELETE FROM cook WHERE id = :recipeId")
    void deleteCook(String recipeId);

    @Query("SELECT * FROM wishlist WHERE id = :recipeId")
    LiveData<CookingEntity> getCook(String recipeId);
}
