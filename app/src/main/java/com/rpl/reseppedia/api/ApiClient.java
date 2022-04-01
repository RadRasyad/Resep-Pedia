package com.rpl.reseppedia.api;


import com.rpl.reseppedia.model.remote.response.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiClient {

    @GET("/api/recipes")
    Call<RecipeResponse> getRecipes();
}
