package com.rpl.reseppedia.di;

import android.content.Context;

import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.LocalDataSource;
import com.rpl.reseppedia.source.local.room.RecipeDB;
import com.rpl.reseppedia.source.remote.RemoteDataSource;
import com.rpl.reseppedia.utils.AppExecutors;

public class Injection {
    public static RecipeRepository provideRepository(Context context) {

        RecipeDB database = RecipeDB.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.recipeDAO());
        AppExecutors appExecutors = new AppExecutors();

        return RecipeRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
