package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class JogoFragment extends Fragment {

    private Integer i;
    public static Integer vitorias;
    public static Integer derrotas;

    private RelativeLayout relativeLayout;

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

    public Button btnVitoria;
    public Button btnEmpate;
    public Button btnDerrota;

    View view;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater
                .inflate(R.layout.jogo_layout,
                        container,
                        false);
        context = getActivity().getApplicationContext();
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rel);
        vitorias = 0;
        derrotas = 0;

        btnDerrota = (Button) view.findViewById(R.id.btnDerrota);
        btnEmpate = (Button) view.findViewById(R.id.btnEmpate);
        btnVitoria = (Button) view.findViewById(R.id.btnVitoria);

        txtViewPokemon = (TextView) view.findViewById(R.id.txtViewNome);
        txtViewTipo = (TextView) view.findViewById(R.id.txtViewTipo);
        txtViewHp = (TextView) view.findViewById(R.id.txtViewHp);
        imgViewPokemon = (ImageView) view.findViewById(R.id.imgViewPokemon);
        imgViewTipo = (ImageView) view.findViewById(R.id.imgViewTipo);
        i = 0;
        setCard(i);

        btnVitoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreJogoFragment.listaCards.remove(i);
                i++;
                if (i < 20) {
                    vitorias++;
                    setCard(i);
                } else {
                    vitorias++;
                    getTelaFimJogo();

                }
            }
        });

        btnEmpate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i < 19){
                    Collections.shuffle(PreJogoFragment.listaCards);
                    setCard(i);
                } else
                {
                    getTelaFimJogo();
                }
            }
        });

        btnDerrota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreJogoFragment.listaCards.remove(i);
                i++;
                if (i < 20) {
                    derrotas++;
                    setCard(i);
                } else {
                    derrotas++;
                    getTelaFimJogo();
                }
            }
        });

        return view;
    }

    public void setCard(Integer index){

        String tipo = PreJogoFragment.listaCards.get(i).getTypes().get(PreJogoFragment.listaCards.get(i).getTypes().size() - 1).getName().trim();

        txtViewPokemon.setText(PreJogoFragment.listaCards.get(index).getName().trim());
        txtViewTipo.setText(tipo);
        txtViewHp.setText(PreJogoFragment.listaCards.get(index).getHp().toString().trim());
        setImg(PreJogoFragment.listaCards.get(i).getSprites());
        setImgTipo(tipo);



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

    public void setImgTipo(String tipo){

        if ( tipo.trim().equals("water")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.agua);
            imgViewTipo.setImageDrawable(myDrawable);
        } else if( tipo.trim().equals("dark")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.dark);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("dragon")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.dragao);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("fairy")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.fada);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("ghost")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.fantasma);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("fire")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.fogo);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("ice")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.gelo);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("ground")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.ground);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("bug")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.inseto);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("fighting")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.lutador);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("steel")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.metal);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("normal")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.normal);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("rock")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.pedra);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("grass")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.planta);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("psychic")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.psiquico);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("eletric")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.trovao);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("poison")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.venenoso);
            imgViewTipo.setImageDrawable(myDrawable);
        }else if( tipo.trim().equals("flying")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.voador);
            imgViewTipo.setImageDrawable(myDrawable);
        }
    }

    public void getTelaFimJogo(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        FimJogoFragment llf = new FimJogoFragment();
        ft.replace(R.id.frameLayout, llf);
        ft.addToBackStack(null);

        ft.commit();
    }
}
