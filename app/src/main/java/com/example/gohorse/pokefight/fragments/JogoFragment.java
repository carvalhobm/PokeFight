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
import com.example.gohorse.pokefight.activities.HomeActivity;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.picasso.Picasso;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class JogoFragment extends Fragment {

    private Integer qntdCartas = 20;
    private Integer i;
    public static Integer vitorias;
    public static Integer derrotas;

    private RelativeLayout relativeLayout;

    public static final String BASE_URL = "http://pokeapi.co";

    public RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final public MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);

    public TextView txtViewNumero;
    public TextView txtViewPokemon;
    public ImageView imgViewPokemon;
    public ImageView imgViewTipo;
    public TextView txtViewTipo;
    public TextView txtViewHpCard;
    public TextView txtViewAtaqueCard;
    public TextView txtViewDefesaCard;
    public TextView txtViewSpAtkCard;
    public TextView txtViewSpDefCard;
    public TextView txtViewSpeedCard;
    public TextView txtViewWeightCard;


    public Button btnVitoria;
    public Button btnEmpate;
    public Button btnDerrota;

    public View view;
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

        imgViewPokemon = (ImageView) view.findViewById(R.id.imgViewPokemon);
        imgViewTipo = (ImageView) view.findViewById(R.id.imgViewTipo);

        txtViewNumero = (TextView) view.findViewById(R.id.txtViewNumero);
        txtViewPokemon = (TextView) view.findViewById(R.id.txtViewNome);
        txtViewTipo = (TextView) view.findViewById(R.id.txtViewTipo);
        txtViewHpCard = (TextView) view.findViewById(R.id.txtViewHpCard);
        txtViewAtaqueCard = (TextView) view.findViewById(R.id.txtViewAtaqueCard);
        txtViewDefesaCard = (TextView) view.findViewById(R.id.txtViewDefesaCard);
        txtViewSpAtkCard = (TextView) view.findViewById(R.id.txtViewSpAtkCard);
        txtViewSpDefCard = (TextView) view.findViewById(R.id.txtviewSpDefCard);
        txtViewSpeedCard = (TextView) view.findViewById(R.id.txtViewSpeedCard);
        txtViewWeightCard = (TextView) view.findViewById(R.id.txtViewWeightCard);


        i = 0;
        setCard(i);

        btnVitoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreJogoFragment.listaCards.remove(i);
                i++;
                if (i < qntdCartas) {
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
                if(i < (qntdCartas-1)){
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
                if (i < qntdCartas) {
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

        txtViewNumero.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getNationalId()).trim());
        txtViewPokemon.setText(PreJogoFragment.listaCards.get(index).getName().trim());
        txtViewTipo.setText(tipo.trim());
        txtViewHpCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getHp().toString()).trim());
        txtViewAtaqueCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getAttack()).trim());
        txtViewDefesaCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getDefense()).trim());
        txtViewSpAtkCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getSpAtk()).trim());
        txtViewSpDefCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getSp_def()).trim());
        txtViewSpeedCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getSpeed()).trim());
        txtViewWeightCard.setText(String.valueOf(PreJogoFragment.listaCards.get(index).getWeight()).trim());

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
        HomeActivity.progressDialog.hide();
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
