package br.com.market.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRepository {
	
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void persist(Object entity) {
    	getSession().persist(entity);
    }

    public void save(Object entity) {
    	getSession().save(entity);
    }

    public void saveOrUpdate(Object entity) {
    	getSession().saveOrUpdate(entity);
    }

    public void update(Object entity) {
        getSession().update(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }

}
