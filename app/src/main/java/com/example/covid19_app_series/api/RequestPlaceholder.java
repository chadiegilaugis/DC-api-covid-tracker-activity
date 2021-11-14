package com.example.covid19_app_series.api;

import com.example.covid19_app_series.pojos.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestPlaceholder {

    @POST("login")
    Call<Login> Login(@Body Login login);
}
