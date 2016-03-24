package br.com.market.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract class AbstractRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
//    public Model findById(Long id) {
//        Query query = getSession().createQuery("from Unidade where id = :id");
//        query.setLong("id", id);
//        Unidade unidade = (Unidade) query.uniqueResult();
//        return unidade;
//    }

}
