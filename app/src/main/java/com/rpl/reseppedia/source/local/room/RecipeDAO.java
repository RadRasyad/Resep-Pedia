package com.rpl.reseppedia.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rpl.reseppedia.source.local.entity.RecipeEntity;

import java.util.List;


@Dao
public interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    DataSource.Factory<Integer, RecipeEntity> getAllRecipe();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(List<RecipeEntity> recipe);
}
