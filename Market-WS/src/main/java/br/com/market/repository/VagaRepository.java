package br.com.market.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.market.infra.repository.EntityRepository;
import br.com.market.model.Vaga;

@Repository
public class VagaRepository extends EntityRepository<Vaga> {
	
	@SuppressWarnings("unchecked")
	public List<Vaga> vagasPorLoja(Long codLoja) {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja = :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Vaga> vagasDiferenteLoja(Long codLoja) {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja <> :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		return query.list();
	}

}
