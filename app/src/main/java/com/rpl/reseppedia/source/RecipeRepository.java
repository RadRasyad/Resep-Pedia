package com.rpl.reseppedia.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rpl.reseppedia.source.local.LocalDataSource;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.remote.response.ApiResponse;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;
import com.rpl.reseppedia.utils.AppExecutors;
import com.rpl.reseppedia.utils.EspressoIdlingResource;
import com.rpl.reseppedia.vo.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeRepository implements RecipeDataSource {

    private volatile static RecipeRepository INSTANCE = null;

        private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    private RecipeRepository( @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {

        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static RecipeRepository getInstance(LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (RecipeRepository.class) {
                INSTANCE = new RecipeRepository(localDataSource, appExecutors);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<RecipeEntity>>> getAllRecipe() {
        return new NetworkBoundResource<PagedList<RecipeEntity>, List<RecipeResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<RecipeEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllRecipe(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<RecipeEntity> data) {
                return true;
            }

            @Override
            protected LiveData<ApiResponse<List<RecipeResponse>>> createCall() {
                EspressoIdlingResource.increment();
                List<RecipeResponse> recipeList = new ArrayList<>();
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
                                    Log.d("Recipe ", String.valueOf(recipe));

                                    recipeList.add(recipe);
                                    Log.d("Objek Resep", String.valueOf(recipeList.size()));

                                }
                            } else {
                                Log.w("Data Resep", "Error getting documents.", task.getException());
                            }
                            resultData.setValue(ApiResponse.success(recipeList));
                            EspressoIdlingResource.decrement();
                        });

                return resultData;
            }

            @Override
            protected void saveCallResult(List<RecipeResponse> data) {
                ArrayList<RecipeEntity> courseList = new ArrayList<>();
                for (RecipeResponse response : data) {
                    RecipeEntity course = new RecipeEntity(
                            response.getId(),
                            response.getNama(),
                            response.getPenulis(),
                            response.getDitulis(),
                            response.getWaktu(),
                            response.getPorsi(),
                            response.getKesulitan(),
                            response.getKategori(),
                            response.getDeskripsi(),
                            response.getFoto(),
                            response.getBahan(),
                            response.getCara_masak());
                    courseList.add(course);
                }
                localDataSource.insertRecipe(courseList);
            }
        }.asLiveData();
    }
}
