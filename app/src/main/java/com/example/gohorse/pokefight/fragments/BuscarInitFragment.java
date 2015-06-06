package com.example.gohorse.pokefight.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.adapter.Information;
import com.example.gohorse.pokefight.adapter.RVAdapterInfo;

import java.util.ArrayList;
import java.util.List;

public class BuscarInitFragment extends Fragment {

    private RecyclerView recyclerView;
    private RVAdapterInfo rvAdapterInfo;

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
        View cardView = inflater.inflate(R.layout.card_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        rvAdapterInfo = new RVAdapterInfo(getActivity(), getData());
        recyclerView.setAdapter(rvAdapterInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private static List<Information> getData(){
        List<Information> data  = new ArrayList<>();
        String[] titles = new String[50];
        for (int i = 0; i < titles.length; i++){
            titles[i] = "" + i;
            Information inf = new Information();
            inf.title = titles[i];
            data.add(inf);
        }
        return data;
    }

}
