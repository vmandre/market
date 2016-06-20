package br.com.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "funcionario_vaga")
public class FuncionarioVaga extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 1146351528470896667L;
	
	private Vaga vaga;
	private Funcionario funcionario;
	private Status status;
	
	public static enum Status {
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_vaga", nullable=false)
	public Vaga getVaga() {
		return vaga;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_funcionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	@Column(name="status", nullable=false)
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
}