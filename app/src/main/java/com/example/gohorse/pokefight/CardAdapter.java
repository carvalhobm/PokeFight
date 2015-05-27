package com.example.gohorse.pokefight;

import com.example.gohorse.pokefight.bean.Card;

import java.util.Collection;

/**
 * Created by Bruno on 26/05/2015.
 */
public class CardAdapter {
    public static Collection<Card> listaCards;

    public static void updateListaCards(Collection<Card> lista){
        listaCards = lista;
    }

}
