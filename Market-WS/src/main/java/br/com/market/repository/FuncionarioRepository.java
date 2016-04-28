package br.com.market.repository;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.market.infra.repository.EntityRepository;
import br.com.market.model.Funcionario;

@Repository
public class FuncionarioRepository extends EntityRepository<Funcionario> {
	
	public Funcionario login(Funcionario entity) {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where matricula = :matricula and senha = :senha");
		Query query = createQuery(hql);
		query.setLong("matricula", entity.getMatricula());
		query.setString("senha", entity.getSenha());
		return (Funcionario) query.uniqueResult();
	}
	

}
