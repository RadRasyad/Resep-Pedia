package com.rpl.reseppedia.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiConfig getApiService() {

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://masak-apa.tomorisakura.vercel.app")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiConfig.class);
    }
}
