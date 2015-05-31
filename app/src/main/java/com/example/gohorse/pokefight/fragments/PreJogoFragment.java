package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Description;
import com.example.gohorse.pokefight.model.DescriptionFinal;
import com.example.gohorse.pokefight.model.Pokemon;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class PreJogoFragment extends Fragment {
    //----GeraçoesPokemon-------------------------------------------------------------------------------
    private Integer minGen1 = 0;
    private Integer maxGen1 = 150;
    private Integer minGen2 = 151;
    private Integer maxGen2 = 300;
    private Integer minGen3;
    private Integer maxGen3;
    private Integer minGen4;
    private Integer maxGen4;
    private Integer minGen5;
    private Integer maxGen5;
    private Integer minGen6;
    private Integer maxGen6;
//----FimGeraçõesPokemon----------------------------------------------------------------------------

    //----RetrofitUtils---------------------------------------------------------------------------------
    public static final String BASE_URL = "http://pokeapi.co";
    private Context context;

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);
//----FimRetrofitUtils------------------------------------------------------------------------------

    View view;
    RelativeLayout relativeLayoutToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pre_jogo_layout, container, false);
        context = getActivity().getApplicationContext();

        setHasOptionsMenu(true);
        return view;
    }


    public void random() {

        Random rand = new Random();
        Integer index = rand.nextInt(718) + 1;


        apiService.getPokemon(index.toString(), new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, retrofit.client.Response response) {

                List<Sprite> sprites = pokemon.getSprites();
                List<Description> descriptions = pokemon.getDescriptions();

                setImg(sprites);
                setDescricao(descriptions);

            }

            @Override
            public void failure(RetrofitError error) {
//                if (error.getMessage().equals("404 NOT FOUND")) {
//                    txtView.setText("Pokemon nao encontrado!");
//                } else if (error.getMessage().contains("Cannot resolve Host")) {
//                    txtView.setText("Erro de rede!");
//                }

            }
        });


    }

    public void setImg(List<Sprite> sprites){
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        apiService.getPokemonSprite(sprites.get(0).getResourceUri().replaceFirst("/", ""), new Callback<SpriteFinal>() {
            @Override
            public void success(SpriteFinal spriteFinal, retrofit.client.Response response) {
                Picasso.with(context).load("http://pokeapi.co"
                                + spriteFinal.getImage()
                ).into(imageView);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAGsetImg", "FALHA");
            }
        });
    }

    public void setDescricao(List<Description> listaDescricoes){
        final TextView txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricao);

        apiService.getPokemonDescription(listaDescricoes.get(0).getResourceUri().replaceFirst("/", ""), new Callback<DescriptionFinal>() {
            @Override
            public void success(DescriptionFinal descriptionFinal, retrofit.client.Response response) {
                txtViewDescricao.setText(descriptionFinal.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAGsetDescricao", "FALHA");
            }
        });
    }
}
