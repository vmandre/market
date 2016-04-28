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
@Table(name = "funcionario")
public class Funcionario extends Identity {

	/** @serialField */
	private static final long serialVersionUID = -3584880229134265318L;
	
	private Long matricula;
	private String senha;
	private String nome;
	private Date dataAdmissao;
	private Loja loja;
	private Cargo cargo;
	private Boolean ativo;
	private Date dataNascimento;
	private Long cpf;
	private String telefone;
	private String celular;
	private String email;
	
	public Long getMatricula() {
		return matricula;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_admissao")
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_loja", nullable=false)
	public Loja getLoja() {
		return loja;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_cargo", nullable=false)
	public Cargo getCargo() {
		return cargo;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public Long getCpf() {
		return cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}