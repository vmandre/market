package br.com.market.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "cargo")
public class Cargo extends Identity {

	/** @serialField */
	private static final long serialVersionUID = -5006655474171146482L;
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
