package com.example.covid19_app_series.api;

import com.example.covid19_app_series.pojos.Login;
import com.example.covid19_app_series.pojos.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestPlaceholder {

    @POST("login")
    Call<Login> Login(@Body Login login);

    @GET("get-all-posts")
//    Call<List<Posts>> getAllPost(@Query("t") String t,@Query("u") String u);
    Call<List<Posts>> getAllPost();
}
