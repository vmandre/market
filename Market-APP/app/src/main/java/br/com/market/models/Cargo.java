package br.com.market.models;

import java.io.Serializable;

public class Cargo extends AbstractModel implements Serializable{

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
