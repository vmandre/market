package br.com.market.repository;

import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.market.infra.repository.EntityRepository;
import br.com.market.model.Holerite;

@Repository
public class HoleriteRepository extends EntityRepository<Holerite> {
	
	public Holerite holeritePorFuncionarioEDataEmissao(Long codFuncionario, Date dataEmissao) {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_funcionario = :codFuncionario and data_geracao = :dataEmissao");
		Query query = createQuery(hql);
		query.setLong("codFuncionario", codFuncionario);
		query.setDate("dataEmissao", dataEmissao);
		return (Holerite) query.uniqueResult();
	}
	

}
