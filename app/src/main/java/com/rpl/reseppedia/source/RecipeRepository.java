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
import com.rpl.reseppedia.source.local.entity.CookingEntity;
import com.rpl.reseppedia.source.local.entity.RecipeEntity;
import com.rpl.reseppedia.source.local.entity.WishlistRecipeEntity;
import com.rpl.reseppedia.source.remote.response.ApiResponse;
import com.rpl.reseppedia.source.remote.response.RecipeResponse;
import com.rpl.reseppedia.utils.AppExecutors;
import com.rpl.reseppedia.utils.EspressoIdlingResource;
import com.rpl.reseppedia.vo.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                        .setPrefetchDistance(5)
                        .setInitialLoadSizeHint(5)
                        .setPageSize(5)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllRecipe(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<RecipeEntity> data) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(localDataSource::delLocalRecipe);
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
                                    RecipeResponse recipe = document.toObject(RecipeResponse.class);
                                    recipeList.add(recipe);
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

    @Override
    public LiveData<RecipeEntity> getRecipeById(final String recipeId) {
        return localDataSource.getRecipeById(recipeId);
    }

    @Override
    public LiveData<PagedList<WishlistRecipeEntity>> getWishlistRecipe() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getAllWhishlist(), config).build();
    }

    @Override
    public LiveData<WishlistRecipeEntity> getWishlistById(final String recipeId) {
        return localDataSource.getWishlistById(recipeId);
    }

    public void insertWishlist(WishlistRecipeEntity recipe) {
        localDataSource.insertWishlist(recipe);
    }

    public void deleteWishlist(String id) {
        localDataSource.deleteWishlist(id);
    }

    public int checkWish(String recipeId){
        return localDataSource.checkWish(recipeId);
    }

    public LiveData<CookingEntity> getCook(String recipeId) {
        return localDataSource.getCook(recipeId);
    }

    public void insertCook(CookingEntity recipe) {
        localDataSource.insertCook(recipe);
    }

    public void deleteCook(String id) {
        localDataSource.deleteCook(id);
    }


    public LiveData<Resource<PagedList<RecipeEntity>>> getAllRecipeByCategories(String kategori) {
        return new NetworkBoundResource<PagedList<RecipeEntity>, List<RecipeResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<RecipeEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPrefetchDistance(4)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllRecipe(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<RecipeEntity> data) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(localDataSource::delLocalRecipe);
                return true;
            }

            @Override
            protected LiveData<ApiResponse<List<RecipeResponse>>> createCall() {
                EspressoIdlingResource.increment();
                List<RecipeResponse> recipeList = new ArrayList<>();
                MutableLiveData<ApiResponse<List<RecipeResponse>>> resultData = new MutableLiveData<>();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Resep")
                        .whereEqualTo("kategori", kategori)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                recipeList.clear();
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                                    RecipeResponse recipe = document.toObject(RecipeResponse.class);
                                    recipeList.add(recipe);

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

    public LiveData<Resource<PagedList<RecipeEntity>>> getRecipeByName(final String recipeName) {
        return new NetworkBoundResource<PagedList<RecipeEntity>, List<RecipeResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<RecipeEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPrefetchDistance(4)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllRecipe(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<RecipeEntity> data) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(localDataSource::delLocalRecipe);
                return true;
            }

            @Override
            protected LiveData<ApiResponse<List<RecipeResponse>>> createCall() {
                EspressoIdlingResource.increment();
                List<RecipeResponse> recipeList = new ArrayList<>();
                MutableLiveData<ApiResponse<List<RecipeResponse>>> resultData = new MutableLiveData<>();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Resep")
                        .whereArrayContains("tag", recipeName)
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
