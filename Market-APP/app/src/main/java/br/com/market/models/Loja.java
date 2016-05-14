package br.com.market.models;

import java.io.Serializable;

public class Loja extends AbstractModel implements Serializable{

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
