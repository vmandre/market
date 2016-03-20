package br.com.market.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.market.model.Unidade;

@Repository
@Transactional
public class UnidadeRepository extends AbstractRepository {
	
//    @SuppressWarnings("unchecked")
//    public List<Unidade> findAllUnidades() {
//        Criteria criteria = getSession().createCriteria(Unidade.class);
//        return (List<Unidade>) criteria.list();
//    }
    
    @SuppressWarnings("unchecked")
    public List<Unidade> findAllUnidades() {
//        Session session = getSession().openSession();
        List<Unidade> unidadeList = getSession().createQuery("from Unidade").list();
//        session.close();
        return unidadeList;
    }

}
