package com.rpl.reseppedia.vm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rpl.reseppedia.di.Injection;
import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.ui.home.HomeViewModel;
import com.rpl.reseppedia.ui.wishlist.WishlistViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final RecipeRepository mRecipeRepository;

    private ViewModelFactory(RecipeRepository recipeRepository) {
        mRecipeRepository = recipeRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mRecipeRepository);
        } else if (modelClass.isAssignableFrom(WishlistViewModel.class)) {
            return (T) new WishlistViewModel(mRecipeRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
