package br.com.market.models;

import java.io.Serializable;
import java.util.Date;

public class Holerite extends AbstractModel implements Serializable{

    private Funcionario funcionario;
    private Date dataEmissao;
    private String path;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
