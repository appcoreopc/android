package com.appcore.fragment.myapplication;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetroFitService {

    private String baseServiceUrl;
    private Retrofit retrofit;

    public RetroFitService(String baseUrl) {
        baseServiceUrl = baseUrl;
        retrofit = new Retrofit.Builder().baseUrl(baseServiceUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
