package com.example.gohorse.pokefight;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.gohorse.pokefight.bean.Card;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Bruno on 26/05/2015.
 */
public class Json extends AsyncTask<String, Void, String> {

    private Context context;

    public Json(Context context){
        this.context = context;
    }

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
        }
        return stringBuilder.toString();
    }

    protected String doInBackground(String... urls) {
        return readJSONFeed(urls[0]);
    }

    protected void onPostExecute(String result) {
        Collection<Card> lista = new ArrayList<Card>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray postalCodesItems = new
                    JSONArray(jsonObject.getString("abilities"));

            //---print out the content of the json feed---
            for (int i = 0; i < postalCodesItems.length(); i++) {
                JSONObject postalCodesItem =
                        postalCodesItems.getJSONObject(i);
                Card card = new Card();
                card.setNome(postalCodesItem.getString("name"));
                lista.add(card);
                Log.d("CARD: ", card.getNome());
                CardAdapter.updateListaCards(lista);
                Toast.makeText(this.context,
                        postalCodesItem.getString("name"),
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
        }
    }


}
