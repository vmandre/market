package br.com.market.models;

import java.io.Serializable;
import java.util.Date;

public class Ferias extends AbstractModel implements Serializable{

    private Funcionario funcionario;
    private Date dataSolicitacao;
    private Date dataInicio;
    private Date dataFim;
    private Status status;
    private String observacao;

    public enum Status {
        AGUARDANDO_APROVACAO("Aguardando Aprovação"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado"),
        CANCELADO("Cancelado");

        private String key;

        private Status(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
