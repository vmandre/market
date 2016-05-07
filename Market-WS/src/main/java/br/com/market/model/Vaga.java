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
@Table(name = "vaga")
public class Vaga extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 7316393009867999565L;
	
	private Date dataAbertura;
	private String descricao;
	private Cargo cargo;
	private Loja loja;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_abertura")
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_cargo", nullable=false)
	public Cargo getCargo() {
		return cargo;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_loja", nullable=false)
	public Loja getLoja() {
		return loja;
	}
	
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
}