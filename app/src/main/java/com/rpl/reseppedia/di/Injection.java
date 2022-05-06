package com.rpl.reseppedia.di;

import android.content.Context;

import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.LocalDataSource;
import com.rpl.reseppedia.utils.AppExecutors;

public class Injection {
    public static RecipeRepository provideRepository(Context context) {

        //RecipeDatabase database = RecipeDatabase.getInstance(context);

        //RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        //LocalDataSource localDataSource = LocalDataSource.getInstance(database.recipeDao());
        AppExecutors appExecutors = new AppExecutors();

        return null;//RecipeRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
