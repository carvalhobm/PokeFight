package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class JogoFragment extends Fragment {

    public static final String BASE_URL = "http://pokeapi.co";

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);

    public TextView txtViewPokemon;
    public ImageView imgViewPokemon;
    public ImageView imgViewTipo;
    public TextView txtViewTipo;
    public TextView txtViewHp;

    View view;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jogo_fragment, container, false);
        context = getActivity().getApplicationContext();

        txtViewPokemon = (TextView) view.findViewById(R.id.txtViewNome);
        txtViewTipo = (TextView) view.findViewById(R.id.txtViewTipo);
        txtViewHp = (TextView) view.findViewById(R.id.txtViewHp);
        imgViewPokemon = (ImageView) view.findViewById(R.id.imgViewPokemon);
        imgViewTipo = (ImageView) view.findViewById(R.id.imgViewTipo);

//        setCard();
//        setImg(PreJogoFragment.listaCards.get(0).getSprites());

        return view;
    }

    public void setCard(){

//        txtViewPokemon.setText(PreJogoFragment.listaCards.get(0).getName());
//        txtViewTipo.setText(PreJogoFragment.listaCards.get(0).getTypes().get(0).getName());
//        txtViewHp.setText(PreJogoFragment.listaCards.get(0).getHp());


    }

    public void setImg(List<Sprite> sprites){
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        apiService.getPokemonSprite(sprites.get(0).getResourceUri().replaceFirst("/", ""), new Callback<SpriteFinal>() {
            @Override
            public void success(SpriteFinal spriteFinal, retrofit.client.Response response) {
                Picasso.with(context).load("http://pokeapi.co"
                                + spriteFinal.getImage()
                ).into(imgViewPokemon);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAGsetImg", "FALHA");
            }
        });
    }

}
