package com.example.gohorse.pokefight.adapter;

/**
 * Created by Bruno on 05/06/2015.
 */
public class Information {
    private int index;
    private String nome;
    private String tipo;

    public Information (int index, String nome, String tipo){
        this.index = index;
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIndex() {
        return index;
    }

    public String getNome() {
        return nome;
    }
}
