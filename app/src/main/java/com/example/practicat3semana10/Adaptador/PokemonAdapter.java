package com.example.practicat3semana10.Adaptador;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicat3semana10.Clase.Pokemon;
import com.example.practicat3semana10.Factories.RetroFactory;
import com.example.practicat3semana10.PokemonActivity;
import com.example.practicat3semana10.R;

import java.util.List;

import com.example.practicat3semana10.Servicios.PokemonService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{
    List<Pokemon> pokes;
    public PokemonAdapter(List<Pokemon> poke){this.pokes=poke;}

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder vh, int position) {
        View itemView = vh.itemView;
        Pokemon poke=pokes.get(position);
        TextView tvCodigo=itemView.findViewById(R.id.tvCodigo);
        TextView tvNombre=itemView.findViewById(R.id.tvNombre);
        ImageView ivAvatar=itemView.findViewById(R.id.ivPoke);
        tvNombre.setText(poke.name);
        tvCodigo.setText(poke.tipoPokemon);
        Picasso.get().load("https://i.ibb.co/d02KL3W/pidgeotto.png").into(ivAvatar);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), PokemonActivity.class);
                String pokemonJSON=new Gson().toJson(poke);
                intent.putExtra("Pokemon",pokemonJSON);
                itemView.getContext().startActivity(intent);
            }
        });
    }
    private void removeItem(int position){
        Pokemon pokemon = pokes.get(position);
        Retrofit retrofit= RetroFactory.build();
        PokemonService service=retrofit.create(PokemonService.class);
        Call<Pokemon> call=service.delete(pokemon.id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()){
                    Log.i("APP_VJ20202","Se elimino correctamente al contacto "+ pokemon.id);
                    pokes.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position, pokes .size());
                }
                else{
                    Log.e("APP_VJ20202","No se pudo eliminar el contacto");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("APP_VJ20202","No nos podemos conectar al servicio web");
            }
        });
    }
    @Override
    public int getItemCount() {
        return pokes.size();
    }
    class PokemonViewHolder extends RecyclerView.ViewHolder{

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
