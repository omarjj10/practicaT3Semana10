package com.example.practicat3semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.practicat3semana10.Adaptador.PokemonAdapter;
import com.example.practicat3semana10.Clase.Pokemon;
import com.example.practicat3semana10.Factories.RetroFactory;
import com.example.practicat3semana10.Servicios.PokemonService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    List<Pokemon>poke=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit= RetroFactory.build();
        PokemonService service = retrofit.create(PokemonService.class);
        Call<List<Pokemon>> call=service.GetPokemon();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                //si hay conectividad
                if(!response.isSuccessful()){
                    Log.e("APP_VJ20202","Error de aplicacion");
                }else{
                    Log.i("APP_VJ20202","Respuesta correcta");
                    Log.i("APP_VJ20202",new Gson().toJson(response.body()));
                    poke=response.body();
                    PokemonAdapter adapter = new PokemonAdapter(poke);
                    RecyclerView rv = findViewById(R.id.rvPokemon);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                //no hay conectividad
                Log.e("APP_VJ20202","No hubo conectividad con el servicio web");
            }
        });
        Button btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), CrearActivity.class);
                startActivity(intent);
            }
        });
    }
}