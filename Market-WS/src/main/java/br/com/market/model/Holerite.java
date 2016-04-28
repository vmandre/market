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
@Table(name = "holerite")
public class Holerite extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 3543045686954909722L;
	
	private Funcionario funcionario;
	private Date dataEmissao;
	private String path;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_funcionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_geracao")
	public Date getDataEmissao() {
		return dataEmissao;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public void setPath(String path) {
		this.path = path;
	}
}