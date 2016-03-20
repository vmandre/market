package br.com.market.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.market.bean.Unidade;

@Repository
public class UnidadeRepository extends AbstractRepository {
	
    @SuppressWarnings("unchecked")
    public List<Unidade> findAllUnidades() {
        Criteria criteria = getSession().createCriteria(Unidade.class);
        return (List<Unidade>) criteria.list();
    }

}
