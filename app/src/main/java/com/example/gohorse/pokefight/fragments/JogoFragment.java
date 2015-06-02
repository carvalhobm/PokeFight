package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gohorse.pokefight.R;

public class JogoFragment extends Fragment {

    View view;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pre_jogo_layout, container, false);
        context = getActivity().getApplicationContext();

        return view;
    }

}
