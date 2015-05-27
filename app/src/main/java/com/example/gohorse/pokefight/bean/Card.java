package com.example.gohorse.pokefight.bean;

/**
 * Created by Bruno on 25/05/2015.
 * Entidade Carta que contem todos os atributos da carta.
 */
public class Card {

    private Integer index;
    private String nome;
    private Integer PontosDeVida;
    private Integer Ataque;
    private Integer AtaqueEspecial;
    private Integer Defesa;
    private Integer DefesaEspecial;
    private Integer VelocidadeDeAtaque;
    private Float Peso;

    public Integer getIndex() { return index; }

    public void setIndex(Integer index) {
        this.index = index;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Integer getPontosDeVida() { return PontosDeVida; }

    public void setPontosDeVida(Integer pontosDeVida) { PontosDeVida = pontosDeVida; }



    public Integer getAtaque() { return Ataque; }

    public void setAtaque(Integer ataque) { Ataque = ataque; }



    public Integer getAtaqueEspecial() { return AtaqueEspecial; }

    public void setAtaqueEspecial(Integer ataqueEspecial) { AtaqueEspecial = ataqueEspecial; }



    public Integer getDefesa() { return Defesa; }

    public void setDefesa(Integer defesa) { Defesa = defesa; }



    public Integer getDefesaEspecial() { return DefesaEspecial; }

    public void setDefesaEspecial(Integer defesaEspecial) { DefesaEspecial = defesaEspecial; }



    public Integer getVelocidadeDeAtaque() { return VelocidadeDeAtaque; }

    public void setVelocidadeDeAtaque(Integer velocidadeDeAtaque) { VelocidadeDeAtaque = velocidadeDeAtaque; }



    public Float getPeso() { return Peso; }

    public void setPeso(Float peso) { Peso = peso; }
}
