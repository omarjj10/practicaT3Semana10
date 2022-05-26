package com.example.practicat3semana10.Factories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFactory {
    public static Retrofit build(){
        return new Retrofit.Builder()
                .baseUrl("https://62851d593060bbd34744fa98.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
