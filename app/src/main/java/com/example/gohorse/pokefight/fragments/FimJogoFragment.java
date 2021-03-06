package com.example.gohorse.pokefight.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.activities.HomeActivity;

public class FimJogoFragment extends Fragment {

    private TextView txtViewSituacao;
    private TextView txtViewVitorias;
    private TextView txtViewDerrotas;
    public static Button btnNovoJogo;

    View view;
    private Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater
                .inflate(R.layout.fim_jogo_fragment,
                        container,
                        false);
        context = getActivity().getApplicationContext();

        txtViewDerrotas = (TextView) view.findViewById(R.id.txtViewDerrotas);
        txtViewVitorias = (TextView) view.findViewById(R.id.txtViewVitorias);
        txtViewSituacao = (TextView) view.findViewById(R.id.txtViewSituacao);
        btnNovoJogo = (Button) view.findViewById(R.id.btnNovoJogo);


        if (isVencedor()){
            txtViewSituacao.setText("Winner!");
            txtViewVitorias.setText((JogoFragment.vitorias) + "");
            txtViewDerrotas.setText((JogoFragment.derrotas) + "");
        } else if (isEmpate()) {
            txtViewSituacao.setText("Draw");
            txtViewVitorias.setText((JogoFragment.vitorias) + "");
            txtViewDerrotas.setText((JogoFragment.derrotas) + "");
        } else {
            txtViewSituacao.setText("Loser!");
            txtViewVitorias.setText((JogoFragment.vitorias) + "");
            txtViewDerrotas.setText((JogoFragment.derrotas) + "");
        }


        btnNovoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTelaPreJogo();
            }
        });

        return view;
    }

    public boolean isVencedor(){
        return (JogoFragment.vitorias > JogoFragment.derrotas);
    }

    public boolean isEmpate(){
        return  (JogoFragment.vitorias == JogoFragment.derrotas);
    }

    public void getTelaPreJogo(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        PreJogoFragment llf = new PreJogoFragment();
        ft.replace(R.id.frameLayout, llf);
        ft.commit();
    }
}
