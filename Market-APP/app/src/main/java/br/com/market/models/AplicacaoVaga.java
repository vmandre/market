package br.com.market.models;

import java.io.Serializable;

public class AplicacaoVaga extends AbstractModel implements Serializable{

    private Funcionario funcionario;
    private Vaga vaga;
    private Status status;

    public enum Status {
        EM_ANALISE("Em análise"), SELECIONADO("Selecionado"), NAO_SELECIONADO("Não selecionado"), APROVADO("Aprovado"), REPROVADO("Reprovado");
        private String value;

        private Status(String value) {
            this.setValue(value);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
