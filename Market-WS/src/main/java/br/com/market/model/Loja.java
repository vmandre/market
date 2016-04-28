package br.com.market.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "loja")
public class Loja extends Identity {

	/** @serialField */
	private static final long serialVersionUID = 2638590761365554871L;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
