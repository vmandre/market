package br.com.market.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.market.infra.repository.EntityRepository;
import br.com.market.model.Noticia;

@Repository
public class NoticiaRepository extends EntityRepository<Noticia> {
	
	@SuppressWarnings("unchecked")
	public List<Noticia> noticiasPorLoja(Long codLoja) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select cod, titulo, categoria ");
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja = :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Noticia> noticiasDiferenteLoja(Long codLoja) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select cod, titulo, categoria ");
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja <> :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Noticia> noticiasListaRapidaPorLoja(Long codLoja, Integer limite) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select cod, titulo, categoria ");
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja = :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		query.setMaxResults(limite);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Noticia> noticiasListaRapidaDiferenteLoja(Long codLoja, Integer limite) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select cod, titulo, categoria ");
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("where cod_loja <> :cod");
		Query query = createQuery(hql);
		query.setLong("cod", codLoja);
		query.setMaxResults(limite);
		return query.list();
	}

}
