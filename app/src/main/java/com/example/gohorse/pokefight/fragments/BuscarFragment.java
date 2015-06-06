package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
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

    public RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final public MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);

    public View view;

//--------------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.buscar_layout, container, false);
        context = getActivity().getApplicationContext();
        setHasOptionsMenu(true);
        lerComponentesTela();

        HomeActivity.editTextToolbar.setFocusable(true);
        HomeActivity.editTextToolbar.setSelection(0);
        HomeActivity.editTextToolbar.requestFocus();
        openKeyboard();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_buscar, menu);
        HomeActivity.relativeLayoutToolbar.setVisibility(View.VISIBLE);

        HomeActivity.editTextToolbar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String pokemon = HomeActivity.editTextToolbar.getText().toString();

                    buscarPokemon(pokemon);
                    setLabelsInvisible();
                    closeKeyboard();
                    HomeActivity.relativeLayoutToolbar.setVisibility(View.GONE);

                    return true;
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        checkFocus();

        if( id == R.id.itemBuscar && HomeActivity.relativeLayoutToolbar.getVisibility() == View.GONE) {

            HomeActivity.relativeLayoutToolbar.setVisibility(View.VISIBLE);

            HomeActivity.editTextToolbar.setFocusable(true);
            HomeActivity.editTextToolbar.setSelection(0);
            HomeActivity.editTextToolbar.requestFocus();


        } else if (id == R.id.itemBuscar && HomeActivity.relativeLayoutToolbar.getVisibility() == View.VISIBLE
                && !HomeActivity.editTextToolbar.getText().toString().equals("")){

            String pokemon = HomeActivity.editTextToolbar.getText().toString();
            buscarPokemon(pokemon);
            HomeActivity.editTextToolbar.setSelection(0);
            HomeActivity.editTextToolbar.requestFocus();

            HomeActivity.editTextToolbar.setText("");
            HomeActivity.relativeLayoutToolbar.setVisibility(View.GONE);

        } else {
            HomeActivity.relativeLayoutToolbar.setVisibility(View.GONE);
        }
        return true;
    }

    public void buscarPokemon(String stringPokemon) {
        HomeActivity.progressDialog.show();

        apiService.getPokemon(stringPokemon.trim(), new Callback<Pokemon>() {
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

            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getMessage().equals("404 NOT FOUND")) {
                    txtView.setText("Pokemon nao encontrado!");
                } else if (error.getMessage().contains("Unable to resolve host")){
                    txtView.setText("Erro de rede!");
                } else{
                    txtView.setText(error.getMessage().toString());
                }
                setLabelsInvisible();
            }
        });


    }

    public void setImg(List<Sprite> sprites){

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
        HomeActivity.progressDialog.hide();
    }

    public void setLabelsVisible(){
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

        lblAtaque.setVisibility(View.INVISIBLE);
        lblDefesa.setVisibility(View.INVISIBLE);
        lblDescricao.setVisibility(View.INVISIBLE);
        lblHp.setVisibility(View.INVISIBLE);
        lblPeso.setVisibility(View.INVISIBLE);
        lblSpAtk.setVisibility(View.INVISIBLE);
        lblSpDefesa.setVisibility(View.INVISIBLE);
        lblVelocidade.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        HomeActivity.editTextToolbar.setText("");
        txtViewDescricao.setText("");
        txtViewHp.setText("");
        txtViewAtaque.setText("");
        txtViewDefesa.setText("");
        txtViewPeso.setText("");
        txtViewSpAtk.setText("");
        txtViewSpDefesa.setText("");
        txtViewVelocidade.setText("");
    }

    public void openKeyboard(){
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(HomeActivity.editTextToolbar, InputMethodManager.SHOW_IMPLICIT);
    }

    public void closeKeyboard(){
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);
    }

    public void checkFocus(){
        HomeActivity.editTextToolbar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    closeKeyboard();
                } else {
                    openKeyboard();
                }
            }
        });
    }

    public void lerComponentesTela(){

        lblDescricao = (TextView) view.findViewById(R.id.lblDescricao);
        lblAtaque = (TextView) view.findViewById(R.id.lblAtaque);
        lblHp = (TextView) view.findViewById(R.id.lblHp);
        lblAtaque = (TextView) view.findViewById(R.id.lblAtaque);
        lblDefesa = (TextView) view.findViewById(R.id.lblDefesa);
        lblPeso = (TextView) view.findViewById(R.id.lblPeso);
        lblSpAtk = (TextView) view.findViewById(R.id.lblSpAtk);
        lblSpDefesa = (TextView) view.findViewById(R.id.lblSpDefesa);
        lblVelocidade = (TextView) view.findViewById(R.id.lblVelocidade);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricao);
        txtView = (TextView) view.findViewById(R.id.txtNoPokemon);
        txtViewHp = (TextView) view.findViewById(R.id.txtViewHp);
        txtViewAtaque = (TextView) view.findViewById(R.id.txtViewAtaque);
        txtViewDefesa = (TextView) view.findViewById(R.id.txtViewDefesa);
        txtViewPeso = (TextView) view.findViewById(R.id.txtViewPeso);
        txtViewSpAtk = (TextView) view.findViewById(R.id.txtViewSpAtaque);
        txtViewSpDefesa = (TextView) view.findViewById(R.id.txtViewSpDefesa);
        txtViewVelocidade = (TextView) view.findViewById(R.id.txtViewVelocidade);
    }
}
