package br.com.market.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "setor")
public class Setor extends Identity {

	/** @serialField */
	private static final long serialVersionUID = -5335698072900549190L;
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
