package com.example.gohorse.pokefight.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.activities.HomeActivity;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Description;
import com.example.gohorse.pokefight.model.DescriptionFinal;
import com.example.gohorse.pokefight.model.Pokemon;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PreJogoFragment extends Fragment {
//----GeracoesPokemon-------------------------------------------------------------------------------
    private Integer minGen1 = 1;
    private Integer maxGen1 = 151;
    private Integer minGen2 = 152;
    private Integer maxGen2 = 251;
    private Integer minGen3 = 252;
    private Integer maxGen3 = 386;
    private Integer minGen4 = 387;
    private Integer maxGen4 = 493;
    private Integer minGen5 = 494;
    private Integer maxGen5 = 649;
    private Integer minGen6 = 650;
    private Integer maxGen6 = 718;
//----FimGeracoesPokemon----------------------------------------------------------------------------

    public static List<Pokemon> listaCards;
//----RetrofitUtils---------------------------------------------------------------------------------
    public static final String BASE_URL = "http://pokeapi.co";


    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);
//----FimRetrofitUtils------------------------------------------------------------------------------

//----Buttons---------------------------------------------------------------------------------------
    RadioButton rbGen1;
    RadioButton rbGen2;
    RadioButton rbGen3;
    RadioButton rbGen4;
    RadioButton rbGen5;
    RadioButton rbGen6;
    RadioButton rbAllGen;
    Button btnJogar;
//----FimButtons------------------------------------------------------------------------------------

    View view;
    private Context context;
    SharedPreferences.Editor editor;
    public boolean isPressed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pre_jogo_layout, container, false);
        context = getActivity().getApplicationContext();

        setHasOptionsMenu(true);
        this.isPressed = false;

        listaCards = new ArrayList<Pokemon>();

        rbGen1 = (RadioButton) view.findViewById(R.id.radioGen1);
        rbGen2 = (RadioButton) view.findViewById(R.id.radioGen2);
        rbGen3 = (RadioButton) view.findViewById(R.id.radioGen3);
        rbGen4 = (RadioButton) view.findViewById(R.id.radioGen4);
        rbGen5 = (RadioButton) view.findViewById(R.id.radioGen5);
        rbGen6 = (RadioButton) view.findViewById(R.id.radioGen6);
        rbAllGen = (RadioButton) view.findViewById(R.id.radioAllGen);
        btnJogar = (Button) view.findViewById(R.id.btnJogar);

        String lastGen = HomeActivity.sharedPreferences.getString("Generation", "kade?");

        editor = HomeActivity.sharedPreferences.edit();

        if (lastGen.equals("Gen1")) {
            rbGen1.setChecked(true);
        }else if (lastGen.equals("Gen2")) {
            rbGen2.setChecked(true);
        }else if (lastGen.equals("Gen3")) {
            rbGen3.setChecked(true);
        }else if (lastGen.equals("Gen4")) {
            rbGen4.setChecked(true);
        }else if (lastGen.equals("Gen5")) {
            rbGen5.setChecked(true);
        }else if (lastGen.equals("Gen6")) {
            rbGen6.setChecked(true);
        }else if (lastGen.equals("AllGen")) {
            rbAllGen.setChecked(true);
        }
        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed) {
                    if (rbGen1.isChecked()) {
                        gerarDeck(minGen1, maxGen1, "Gen1");
                        Log.d("TAG", "gen1");
                    } else if (rbGen2.isChecked()) {
                        gerarDeck(minGen2, maxGen2, "Gen2");
                        Log.d("TAG", "gen2");
                    } else if (rbGen3.isChecked()) {
                        gerarDeck(minGen3, maxGen3, "Gen3");
                        Log.d("TAG", "gen3");
                    } else if (rbGen4.isChecked()) {
                        gerarDeck(minGen4, maxGen4, "Gen4");
                        Log.d("TAG", "gen4");
                    } else if (rbGen5.isChecked()) {
                        gerarDeck(minGen5, maxGen5, "Gen5");
                        Log.d("TAG", "gen5");
                    } else if (rbGen6.isChecked()) {
                        gerarDeck(minGen6, maxGen6, "Gen6");
                        Log.d("TAG", "gen6");
                    } else if (rbAllGen.isChecked()) {
                        gerarDeck(minGen1, maxGen6, "AllGen");
                        Log.d("TAG", "Allgen");
                    }
                    isPressed = true;
                    HomeActivity.progressDialog.show();
                }
            }
        });
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
                String[] erro = error.getMessage().toString().split(":");

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

    public void gerarDeck(Integer init, Integer fim, String gen){

        editor.putString("Generation", gen);
        editor.commit();

        int i = 0;

        Random rd = new Random();
        Integer num = 0;
        List<Integer> listaNumeros = new ArrayList<Integer>();
        String index = "";


        while(i < 20){

            do {
                num = rd.nextInt(fim);
                index = "" + num;

                if(listaNumeros.contains(num)){
                    num = -1;
                    index = "" + num;
                }else{
                    listaNumeros.add(num);
                }
                Log.d("WHILE", index);
            }while(num < init);

            final int finalI = i;
            apiService.getPokemon(index, new Callback<Pokemon>() {
                @Override
                public void success(Pokemon pokemon, Response response) {
                    listaCards.add(pokemon);
                    Log.d("TAG", pokemon.getName());
                    if(finalI == 19){
                        getTelaJogo();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            i++;
        }

    }

    public void getTelaJogo(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        JogoFragment llf = new JogoFragment();
        ft.replace(R.id.frameLayout, llf);
        ft.commit();
    }

}
