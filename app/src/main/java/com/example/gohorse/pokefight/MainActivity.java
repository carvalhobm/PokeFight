package com.example.gohorse.pokefight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gohorse.pokefight.bean.Card;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.RestAdapter;


public class MainActivity extends ActionBarActivity {

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
        Log.d("MAIN", "IOU");
        TextView txtView = (TextView) findViewById(R.id.txtNoPokemon);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        String url = "http://pokeapi.co/api/v1/pokemon/1";
        Log.d("URL:", url);

        String nome = "ABC";

        txtView.setText(nome);

        Picasso.with(this).load("http://pokeapi.co/media/img/1383571573.78.png").into(imageView);

        Json json = new Json(getApplicationContext());
        String string = json.readJSONFeed(url);

        new Json(getApplicationContext()).execute(
                url);


        Log.d("TAG", string);
//        for (Card card : CardAdapter.listaCards) {
//            txtView.setText(card.getNome());
//            break;
//        }
    }
}
