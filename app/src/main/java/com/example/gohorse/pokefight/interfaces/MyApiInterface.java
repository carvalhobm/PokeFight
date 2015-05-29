package com.example.gohorse.pokefight.interfaces;

import com.example.gohorse.pokefight.model.DescriptionFinal;
import com.example.gohorse.pokefight.model.Pokemon;
import com.example.gohorse.pokefight.model.SpriteFinal;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Bruno on 28/05/2015.
 */
public interface MyApiInterface {

    @GET("/api/v1/pokemon/{index}/")
    void getPokemon(@Path("index") String index, Callback<Pokemon> cb);

    @GET("/{spriteUri}")
    void getPokemonSprite(@Path("spriteUri") String spriteUri, Callback<SpriteFinal> cb);

    @GET("/{descriptionUri}")
    void getPokemonDescription(@Path("descriptionUri") String descriptionUri, Callback<DescriptionFinal> cb);

}
