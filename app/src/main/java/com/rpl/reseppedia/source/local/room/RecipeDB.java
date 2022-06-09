package com.rpl.reseppedia.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rpl.reseppedia.source.local.entity.CookingEntity;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;

@Database(entities = {RecipeEntity.class, WishlistRecipeEntity.class, CookingEntity.class},
    version = 1,
    exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RecipeDB extends RoomDatabase {
    public abstract RecipeDAO recipeDAO();

    public static volatile RecipeDB INSTANCE;

    public static RecipeDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDB.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeDB.class, "Recipe.db").build();
            }
        }
        return INSTANCE;
    }
}
