package com.example.gohorse.pokefight.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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


public class MainActivity extends ActionBarActivity {


    public static final String BASE_URL = "http://pokeapi.co";
    private Context context = null;

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buscar(View view) {
        this.context = this;

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        EditText editText = (EditText) findViewById(R.id.editText);

        final TextView txtView = (TextView) findViewById(R.id.txtNoPokemon);
        final TextView txtViewHp = (TextView) findViewById(R.id.txtViewHp);
        final TextView txtViewAtaque = (TextView) findViewById(R.id.txtViewAtaque);
        final TextView txtViewDefesa = (TextView) findViewById(R.id.txtViewDefesa);
        final TextView txtViewPeso = (TextView) findViewById(R.id.txtViewPeso);
        final TextView txtViewSpAtk = (TextView) findViewById(R.id.txtViewSpAtaque);
        final TextView txtViewSpDefesa = (TextView) findViewById(R.id.txtViewSpDefesa);
        final TextView txtViewVelocidade = (TextView) findViewById(R.id.txtViewVelocidade);

        apiService.getPokemon(editText.getText().toString(), new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, retrofit.client.Response response) {

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

                Log.d("DEFESA", pokemon.getDefense().toString().trim());
            }

            @Override
            public void failure(RetrofitError error) {
                txtView.setText("ERRO!!");
            }
        });


    }

    public void random(View view) {
        this.context = this;

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Random rand = new Random();
        Integer index = rand.nextInt(600) + 1;

        final TextView txtView = (TextView) findViewById(R.id.txtNoPokemon);
        final TextView txtViewHp = (TextView) findViewById(R.id.txtViewHp);
        final TextView txtViewAtaque = (TextView) findViewById(R.id.txtViewAtaque);
        final TextView txtViewDefesa = (TextView) findViewById(R.id.txtViewDefesa);
        final TextView txtViewPeso = (TextView) findViewById(R.id.txtViewPeso);
        final TextView txtViewSpAtk = (TextView) findViewById(R.id.txtViewSpAtaque);
        final TextView txtViewSpDefesa = (TextView) findViewById(R.id.txtViewSpDefesa);
        final TextView txtViewVelocidade = (TextView) findViewById(R.id.txtViewVelocidade);

        apiService.getPokemon(index.toString(), new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, retrofit.client.Response response) {

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

                Log.d("DEFESA", pokemon.getDefense().toString().trim());
            }

            @Override
            public void failure(RetrofitError error) {
                txtView.setText("ERRO!!");
            }
        });


    }

    public void setImg(List<Sprite> sprites){
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

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
        final TextView txtViewDescricao = (TextView) findViewById(R.id.txtViewDescricao);

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

        final TextView lblDescricao = (TextView) findViewById(R.id.lblDescricao);
        final TextView lblHp = (TextView) findViewById(R.id.lblHp);
        final TextView lblAtaque = (TextView) findViewById(R.id.lblAtaque);
        final TextView lblDefesa = (TextView) findViewById(R.id.lblDefesa);
        final TextView lblPeso = (TextView) findViewById(R.id.lblPeso);
        final TextView lblSpAtk = (TextView) findViewById(R.id.lblSpAtk);
        final TextView lblSpDefesa = (TextView) findViewById(R.id.lblSpDefesa);
        final TextView lblVelocidade = (TextView) findViewById(R.id.lblVelocidade);

        lblAtaque.setVisibility(View.VISIBLE);
        lblDefesa.setVisibility(View.VISIBLE);
        lblDescricao.setVisibility(View.VISIBLE);
        lblHp.setVisibility(View.VISIBLE);
        lblPeso.setVisibility(View.VISIBLE);
        lblSpAtk.setVisibility(View.VISIBLE);
        lblSpDefesa.setVisibility(View.VISIBLE);
        lblVelocidade.setVisibility(View.VISIBLE);

    }

}
