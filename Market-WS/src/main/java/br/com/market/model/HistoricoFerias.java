package br.com.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "historico_ferias")
public class HistoricoFerias extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 2856444406955617316L;
	
	private Funcionario funcionario;
	private Date dataSolicitacao;
	private Date dataInicio;
	private Date dataFim;
	private Status status;
	private String observacao;
	
	public enum Status {
		AGUARDANDO_APROVACAO ("Aguardando Aprovação"),
		APROVADO ("Aprovado"),
		REPROVADO ("Reprovado"),
		CANCELADO ("Cancelado");
		
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_funcionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_solicitacao")
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	public Date getDataInicio() {
		return dataInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	public Date getDataFim() {
		return dataFim;
	}

	public Status getStatus() {
		return status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}