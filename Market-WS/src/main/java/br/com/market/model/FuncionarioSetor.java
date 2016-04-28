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
@Table(name = "funcionario_setor")
public class FuncionarioSetor extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 8957837769926778944L;
	
	private Funcionario funcionario;
	private Setor setor;
	private Date dataInicio;
	private Date dataFim;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_funcionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_setor", nullable=false)
	public Setor getSetor() {
		return setor;
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
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}