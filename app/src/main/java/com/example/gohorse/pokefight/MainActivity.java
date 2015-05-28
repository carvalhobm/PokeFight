package com.example.gohorse.pokefight;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Pokemon;
import com.example.gohorse.pokefight.model.Sprite;
import com.example.gohorse.pokefight.model.SpriteFinal;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class MainActivity extends ActionBarActivity {


    public static final String BASE_URL = "http://pokeapi.co";
    private Context context = null;


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

        final TextView txtView = (TextView) findViewById(R.id.txtNoPokemon);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        EditText editText = (EditText) findViewById(R.id.editText);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        final MyApiInterface apiService =
                restAdapter.create(MyApiInterface.class);

        apiService.getPokemonIndex(editText.getText().toString(), new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, retrofit.client.Response response) {

                List<Sprite> sprites = pokemon.getSprites();
                txtView.setText(pokemon.getName());

                Log.d("SPRITE URI", sprites.get(0).getName());
                Log.d("SPRITE URI", sprites.get(0).getResourceUri());

                String img = getImg(sprites);

                Picasso.with(context)
                        .load(img)
                        .into(imageView);
            }

            @Override
            public void failure(RetrofitError error) {
                txtView.setText("ERRO!!");
            }
        });


    }

    public String getImg(List<Sprite> sprites){

        Log.d("TAG 1", "ENTROU");
        Log.d("TAg2", sprites.get(0).getResourceUri());

        final String[] retorno = {"http://cdn.minilua.com/wp-content/uploads/2010/06/bozo.jpg"};

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        final MyApiInterface apiService =
                restAdapter.create(MyApiInterface.class);

        apiService.getPokemonSprite(sprites.get(0).getResourceUri(), new Callback<SpriteFinal>() {
            @Override
            public void success(SpriteFinal spriteFinal, retrofit.client.Response response) {

                retorno[0] = spriteFinal.getImage();
                Log.d("TAG SPRITE", spriteFinal.getImage());

//                Picasso.with(context).load("http://pokeapi.co"
//                                + spriteFinal.getImage()
////                                        + "/media/img/1.png"
//                ).into(imageView);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAGsetImg", "FALHA");
                retorno[0] = "http://cdn.minilua.com/wp-content/uploads/2010/06/bozo.jpg";
            }
        });

        Log.d("TAG3", retorno[0]);
        return retorno[0];
    }

}
