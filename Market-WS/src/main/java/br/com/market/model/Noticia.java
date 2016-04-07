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
@Table(name = "noticia")
public class Noticia extends Identity {

	/** @serialField	 */
	private static final long serialVersionUID = -6712080756115306276L;
	
	private String titulo;
	private String texto;
	private Date dataInicio;
	private Date dataFim;
	private Loja loja;
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getTexto() {
		return texto;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_loja", nullable=false)
	public Loja getLoja() {
		return loja;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
}