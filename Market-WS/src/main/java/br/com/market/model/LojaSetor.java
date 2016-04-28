package br.com.market.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "loja_setor")
public class LojaSetor extends Identity {

	/** @serialField */
	private static final long serialVersionUID = -4842845114951491293L;
	private Loja loja;
	private Setor setor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_loja", nullable=false)
	public Loja getLoja() {
		return loja;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_setor", nullable=false)
	public Setor getSetor() {
		return setor;
	}
	
	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}