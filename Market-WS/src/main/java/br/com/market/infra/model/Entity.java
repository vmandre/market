package br.com.market.infra.model;

import java.io.Serializable;

public abstract class Entity<Cod extends Serializable> implements Serializable {
	
	/** @serialField */
	private static final long serialVersionUID = -5722126569547428139L;
	protected Cod cod;
	
	public Cod getCod() {
		return cod;
	}
	
	public void setCod(Cod cod) {
		this.cod = cod;
	}
}