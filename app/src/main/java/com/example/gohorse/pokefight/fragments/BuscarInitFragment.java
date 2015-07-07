package com.example.gohorse.pokefight.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.activities.HomeActivity;
import com.example.gohorse.pokefight.adapter.Information;
import com.example.gohorse.pokefight.adapter.RVAdapterInfo;
import com.example.gohorse.pokefight.interfaces.MyApiInterface;
import com.example.gohorse.pokefight.model.Pokemon;
import com.example.gohorse.pokefight.utils.DataHelper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BuscarInitFragment extends Fragment {

    public static final String BASE_URL = "http://pokeapi.co";

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    final MyApiInterface apiService =
            restAdapter.create(MyApiInterface.class);

    private static int qntPokemon = 151;

    private RecyclerView recyclerView;
    private RVAdapterInfo rvAdapterInfo;

    private DataHelper dataHelper;

    public BuscarInitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.buscar_layout_init, container, false);

        dataHelper = new DataHelper(getActivity());
        saveAllPokemon();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        rvAdapterInfo = new RVAdapterInfo(getActivity(), getData());
        recyclerView.setAdapter(rvAdapterInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration
//                                          .Builder(getActivity()
//                                          .getApplicationContext())
//                                          .build());

        HomeActivity.progressDialogSalvandoBd.hide();

        return view;
    }

    private List<Information> getData() {
        List<Information> data = dataHelper.selectAll();
        Log.d("getDta", String.valueOf(data.size()));
        return data;
    }

    Information info;
    int index;

    public void saveAllPokemon() {

        HomeActivity.progressDialogSalvandoBd.show();
        Log.d("LIsta", String.valueOf(dataHelper.selectAll().size()));
        if (!(dataHelper.selectAll().size() == qntPokemon)) {
            dataHelper.deleteAll();
            index = 1;

            while (index <= qntPokemon) {

                apiService.getPokemon(String.valueOf(index), new Callback<Pokemon>() {
                    @Override
                    public void success(Pokemon pokemon, Response response) {
                        String nome = pokemon.getName();
                        String tipo = pokemon.getTypes().get(pokemon.getTypes().size() - 1).getName().trim();

                        info = new Information(pokemon.getNationalId(), nome, tipo);
                        dataHelper.insert(info);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                index++;
            }
        }
        HomeActivity.progressDialogSalvandoBd.hide();
    }

}
