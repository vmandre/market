package br.com.market.models;

import java.io.Serializable;

public class ErroMarket extends AbstractModel implements Serializable{

    private Integer codigo;
    private String mensagem;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
