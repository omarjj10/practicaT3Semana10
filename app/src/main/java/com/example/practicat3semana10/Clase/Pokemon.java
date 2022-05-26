package com.example.practicat3semana10.Clase;

public class Pokemon {
    public int id;
    public String name;
    public String tipoPokemon;
    public String region;
    public String avatar;

    public Pokemon() {
    }
    public Pokemon(String name, String tipoPokemon){
        this.name = name;
        this.tipoPokemon = tipoPokemon;
    }
    public Pokemon(int id, String name, String tipoPokemon) {
        this.id = id;
        this.name = name;
        this.tipoPokemon = tipoPokemon;
    }
    public Pokemon(int id, String name, String tipoPokemon,String region) {
        this.id = id;
        this.name = name;
        this.tipoPokemon = tipoPokemon;
        this.region=region;
    }

    public Pokemon(int id, String name, String tipoPokemon, String region, String avatar) {
        this.id = id;
        this.name = name;
        this.tipoPokemon = tipoPokemon;
        this.region = region;
        this.avatar = avatar;
    }
}
