package com.example.practicat3semana10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicat3semana10.Clase.Pokemon;
import com.example.practicat3semana10.Factories.RetroFactory;
import com.example.practicat3semana10.Servicios.PokemonService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CrearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        Button btn=findViewById(R.id.btnAgregar);
        EditText etNombre=findViewById(R.id.edName);
        EditText etTipo=findViewById(R.id.edTipo);
        EditText etRegion=findViewById(R.id.edRegion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetroFactory.build();
                PokemonService service = retrofit.create(PokemonService.class);
                Pokemon pokemon = new Pokemon();
                pokemon.name=String.valueOf(etNombre.getText());
                pokemon.tipoPokemon=String.valueOf(etTipo.getText());
                pokemon.region=String.valueOf(etRegion.getText());
                Call<Pokemon> call=service.create(pokemon);
                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        if(response.isSuccessful()){
                            Log.i("APP_VJ20202", new Gson().toJson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        Log.e("APP_VJ20202","No nos podemos conectar al servicio web");
                    }
                });
            }
        });
    }
}