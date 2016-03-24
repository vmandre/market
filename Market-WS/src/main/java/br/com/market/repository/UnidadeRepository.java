package br.com.market.repository;

import org.springframework.stereotype.Repository;

import br.com.market.infra.repository.EntityRepository;
import br.com.market.model.Unidade;

@Repository
//@Transactional
public class UnidadeRepository extends EntityRepository<Unidade> {
	
//	@SuppressWarnings("unchecked")
//	public List<Unidade> findAllUnidades() {
//		List<Unidade> unidadeList = getSession().createQuery("from Unidade").list();
//		return unidadeList;
//	}
//	
//    public Unidade findAUnidade(Long id) {
//        Query query = getSession().createQuery("from Unidade where id = :id");
//        query.setLong("id", id);
//        Unidade unidade = (Unidade) query.uniqueResult();
//        return unidade;
//    }

}
