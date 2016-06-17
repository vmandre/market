package br.com.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private Categoria categoria;
	
	public enum Categoria {
		INFORME_LOCAL("Informativo local"), INFORME_GERAL("Informe geral"), DICA("Dica"), ACAO("Ação"), NOVIDADES("Novidades");
		
		private String value;
		
		private Categoria(String value) {
			this.setValue(value);
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	@Column(name="titulo")
	public String getTitulo() {
		return titulo;
	}
	
	@Column(name="texto")
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_loja", nullable=false)
	public Loja getLoja() {
		return loja;
	}
	
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)	
	public Categoria getCategoria() {
		return categoria;
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

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}