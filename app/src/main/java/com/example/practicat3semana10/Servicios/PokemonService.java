package com.example.practicat3semana10.Servicios;

import com.example.practicat3semana10.Clase.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("pokemon")
    Call<List<Pokemon>> GetPokemon();
    @GET("pokemon/{id}")
    Call<Pokemon> findPokemon(@Path("id") int a);
    @POST("pokemon")
    Call<Pokemon>create(@Body Pokemon poke);
    @PUT("pokemon/{id}")
    Call<Pokemon>update(@Path("id") int a,@Body Pokemon poke);
    @DELETE("pokemon/{id}")
    Call<Pokemon>delete(@Path("id") int a);
}
