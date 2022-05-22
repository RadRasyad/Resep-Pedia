package com.rpl.reseppedia.source.remote;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rpl.reseppedia.source.remote.response.ApiResponse;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;
import com.rpl.reseppedia.utils.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;


    public static RemoteDataSource getInstance() {
        return INSTANCE;
    }

    //nanti firebase disini
    @NonNull
    public LiveData<ApiResponse<List<RecipeResponse>>> getRecipe() {
        EspressoIdlingResource.increment();
        ArrayList<RecipeResponse> recipeList = new ArrayList<>();
        MutableLiveData<ApiResponse<List<RecipeResponse>>> resultData = new MutableLiveData<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Resep")
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        recipeList.clear();
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Log.d("Data Resep", document.getId() + " => " + document.getData());

                            RecipeResponse recipe = document.toObject(RecipeResponse.class);
                            recipeList.add(recipe);
                            Log.d("Objek Resep", String.valueOf(recipeList.size()));
                            if (recipeList!=null) {
                                resultData.setValue(ApiResponse.success(recipeList));
                            } else {
                                resultData.setValue(ApiResponse.error("Data Kosong",recipeList));
                            }
                            EspressoIdlingResource.decrement();

                        }
                    } else {
                        Log.w("Data Resep", "Error getting documents.", task.getException());
                    }
                });
        return resultData;
    }

}
