package com.rpl.reseppedia.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.rpl.reseppedia.source.local.entity.RecipeEntity;

import java.util.List;

@Dao
public interface RecipeDAO {

    LiveData<List<RecipeEntity>> getRecipe();
}
