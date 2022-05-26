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

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        String pokemonJSON=getIntent().getStringExtra("Pokemon");
        Pokemon poke= new Gson().fromJson(pokemonJSON,Pokemon.class);
        ImageView ivAvatar=findViewById(R.id.ivPoke);
        TextView tvCodigo=findViewById(R.id.tvCodigo);
        TextView tvNombre=findViewById(R.id.tvNombre);
        Picasso.get().load("https://i.ibb.co/d02KL3W/pidgeotto.png").into(ivAvatar);
        tvCodigo.setText(poke.tipoPokemon);
        tvNombre.setText(poke.name);
    }
}