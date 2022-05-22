package com.rpl.reseppedia.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rpl.reseppedia.source.RecipeRepository;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;
import com.rpl.reseppedia.vo.Resource;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private RecipeRepository recipeRepository;
    //buat coba2 firebase disini dulu
    private ArrayList<RecipeResponse> recipeList = new ArrayList<>();


    /*
    public ArrayList<RecipeResponse> getRecipe() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Resep")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            recipeList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Data Resep", document.getId() + " => " + document.getData());

                                RecipeResponse recipe = document.toObject(RecipeResponse.class);
                                recipeList.add(recipe);
                                Log.d("Objek Resep", String.valueOf(recipeList.size()));
                            }
                        } else {
                            Log.w("Data Resep", "Error getting documents.", task.getException());
                        }
                    }
                });
        return recipeList;
    }
    */



    public HomeViewModel(RecipeRepository mRecipeRepository) {
        this.recipeRepository = mRecipeRepository;
    }

    public LiveData<Resource<PagedList<RecipeEntity>>> getRecipe() {
        return recipeRepository.getAllRecipe();
    }

}