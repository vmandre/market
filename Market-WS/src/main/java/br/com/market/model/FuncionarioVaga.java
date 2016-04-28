package br.com.market.model;

import javax.persistence.Entity;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_vaga", nullable=false)
	public Vaga getVaga() {
		return vaga;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_funcionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}