package br.com.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.market.infra.model.Identity;

@Entity
@Table(name = "unidade")
public class Unidade extends Identity {

    /** @serialField */
	private static final long serialVersionUID = 7252108219617268661L;
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
    
    @Column(name = "nome", nullable = false)
    private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    @Override
    public String toString() {
        return "Unidade [ id=" + getId() + ", nome=" + nome + " ]";
    }

}