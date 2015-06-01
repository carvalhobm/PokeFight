package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BuscarFragment extends Fragment {

//----Campos e Labels------------------------------------------------------------------------------------

    private ImageView imageView;
    private EditText editText;

    private TextView txtView;
    private TextView txtViewDescricao;
    private TextView txtViewHp;
    private TextView txtViewAtaque;
    private TextView txtViewDefesa;
    private TextView txtViewPeso;
    private TextView txtViewSpAtk;
    private TextView txtViewSpDefesa;
    private TextView txtViewVelocidade;

    private TextView lblDescricao;
    private TextView lblHp;
    private TextView lblAtaque;
    private TextView lblDefesa;
    private TextView lblPeso;
    private TextView lblSpAtk;
    private TextView lblSpDefesa;
    private TextView lblVelocidade;

//--------------------------------------------------------------------------------------------------

    private InputMethodManager imm;
    public static final String BASE_URL = "http://pokeapi.co";
    private Context context;

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);

    View view;
    RelativeLayout relativeLayoutToolbar;

//--------------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.buscar_layout, container, false);
        context = getActivity().getApplicationContext();
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_buscar, menu);
        HomeActivity.relativeLayoutToolbar.setVisibility(View.VISIBLE);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if( id == R.id.itemBuscar && HomeActivity.relativeLayoutToolbar.getVisibility() == View.GONE) {
            imm.showSoftInput(view, 1);
            HomeActivity.editTextToolbar.setFocusable(true);

            HomeActivity.relativeLayoutToolbar.setVisibility(View.VISIBLE);

        } else if (id == R.id.itemBuscar && HomeActivity.relativeLayoutToolbar.getVisibility() == View.VISIBLE
                && !HomeActivity.editTextToolbar.getText().toString().equals("")){
            imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);

            String pokemon = HomeActivity.editTextToolbar.getText().toString();
            buscarPokemon(pokemon);

            HomeActivity.editTextToolbar.setText("");
            HomeActivity.relativeLayoutToolbar.setVisibility(View.GONE);

        } else {
            imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);
            HomeActivity.relativeLayoutToolbar.setVisibility(View.GONE);
        }
        return true;
    }

    public void buscarPokemon(String stringPokemon) {

        imageView = (ImageView) view.findViewById(R.id.imageView);

        txtView = (TextView) view.findViewById(R.id.txtNoPokemon);
        txtViewHp = (TextView) view.findViewById(R.id.txtViewHp);
        txtViewAtaque = (TextView) view.findViewById(R.id.txtViewAtaque);
        txtViewDefesa = (TextView) view.findViewById(R.id.txtViewDefesa);
        txtViewPeso = (TextView) view.findViewById(R.id.txtViewPeso);
        txtViewSpAtk = (TextView) view.findViewById(R.id.txtViewSpAtaque);
        txtViewSpDefesa = (TextView) view.findViewById(R.id.txtViewSpDefesa);
        txtViewVelocidade = (TextView) view.findViewById(R.id.txtViewVelocidade);

        apiService.getPokemon(stringPokemon, new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, Response response) {

                setLabelsInvisible();

                List<Sprite> sprites = pokemon.getSprites();
                List<Description> descriptions = pokemon.getDescriptions();

                setLabelsVisible();

                setImg(sprites);
                setDescricao(descriptions);

                txtView.setText(pokemon.getName());
                txtViewHp.setText(pokemon.getHp().toString());
                txtViewAtaque.setText(pokemon.getAttack().toString());
                txtViewDefesa.setText(pokemon.getDefense().toString().trim());
                txtViewPeso.setText(pokemon.getWeight().toString());
                txtViewSpAtk.setText(pokemon.getSpAtk().toString());
                txtViewSpDefesa.setText(pokemon.getSp_def().toString());
                txtViewVelocidade.setText(pokemon.getSpeed().toString());

                imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);
            }

            @Override
            public void failure(RetrofitError error) {
//                if (error.getMessage().equals("404 NOT FOUND")) {
//                    txtView.setText("Pokemon nao encontrado!");
//                } else if (error.getMessage().contains("Unable to resolve host")){
//                    txtView.setText("Erro de rede!");
//                } else{
//                    txtView.setText(error.getMessage().toString());
//                }
                txtView.setText(error.getMessage().toString());
                setLabelsInvisible();
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

    public void setLabelsVisible(){

        final TextView lblDescricao = (TextView) view.findViewById(R.id.lblDescricao);
        final TextView lblHp = (TextView) view.findViewById(R.id.lblHp);
        final TextView lblAtaque = (TextView) view.findViewById(R.id.lblAtaque);
        final TextView lblDefesa = (TextView) view.findViewById(R.id.lblDefesa);
        final TextView lblPeso = (TextView) view.findViewById(R.id.lblPeso);
        final TextView lblSpAtk = (TextView) view.findViewById(R.id.lblSpAtk);
        final TextView lblSpDefesa = (TextView) view.findViewById(R.id.lblSpDefesa);
        final TextView lblVelocidade = (TextView) view.findViewById(R.id.lblVelocidade);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        lblAtaque.setVisibility(View.VISIBLE);
        lblDefesa.setVisibility(View.VISIBLE);
        lblDescricao.setVisibility(View.VISIBLE);
        lblHp.setVisibility(View.VISIBLE);
        lblPeso.setVisibility(View.VISIBLE);
        lblSpAtk.setVisibility(View.VISIBLE);
        lblSpDefesa.setVisibility(View.VISIBLE);
        lblVelocidade.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);

    }

    public void setLabelsInvisible(){

        final TextView lblDescricao = (TextView) view.findViewById(R.id.lblDescricao);
        final TextView lblHp = (TextView) view.findViewById(R.id.lblHp);
        final TextView lblAtaque = (TextView) view.findViewById(R.id.lblAtaque);
        final TextView lblDefesa = (TextView) view.findViewById(R.id.lblDefesa);
        final TextView lblPeso = (TextView) view.findViewById(R.id.lblPeso);
        final TextView lblSpAtk = (TextView) view.findViewById(R.id.lblSpAtk);
        final TextView lblSpDefesa = (TextView) view.findViewById(R.id.lblSpDefesa);
        final TextView lblVelocidade = (TextView) view.findViewById(R.id.lblVelocidade);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricao);
        txtViewHp = (TextView) view.findViewById(R.id.txtViewHp);
        txtViewAtaque = (TextView) view.findViewById(R.id.txtViewAtaque);
        txtViewDefesa = (TextView) view.findViewById(R.id.txtViewDefesa);
        txtViewPeso = (TextView) view.findViewById(R.id.txtViewPeso);
        txtViewSpAtk = (TextView) view.findViewById(R.id.txtViewSpAtaque);
        txtViewSpDefesa = (TextView) view.findViewById(R.id.txtViewSpDefesa);
        final TextView txtViewVelocidade = (TextView) view.findViewById(R.id.txtViewVelocidade);

        lblAtaque.setVisibility(View.INVISIBLE);
        lblDefesa.setVisibility(View.INVISIBLE);
        lblDescricao.setVisibility(View.INVISIBLE);
        lblHp.setVisibility(View.INVISIBLE);
        lblPeso.setVisibility(View.INVISIBLE);
        lblSpAtk.setVisibility(View.INVISIBLE);
        lblSpDefesa.setVisibility(View.INVISIBLE);
        lblVelocidade.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        txtViewDescricao.setText("");
        txtViewHp.setText("");
        txtViewAtaque.setText("");
        txtViewDefesa.setText("");
        txtViewPeso.setText("");
        txtViewSpAtk.setText("");
        txtViewSpDefesa.setText("");
        txtViewVelocidade.setText("");
    }
}
