package br.com.market.infra.model;

import java.io.Serializable;

public abstract class Entity<ID extends Serializable> implements Serializable {
	
	/** @serialField */
	private static final long serialVersionUID = -5722126569547428139L;
	protected ID id;
	
	public ID getId() {
		return id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
}