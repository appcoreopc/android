package com.appcore.fragment.myapplication;

import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DataService {
    @GET("/apitest/api/test")
    Call<List<Employee>> getProduct();

    @GET("/apitest/api/test/{id}")
    Call<List<Employee>> getProduct(@Path("id") String productId);
}
