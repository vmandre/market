package br.com.market.infra.repository;

import java.util.Collection;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.market.infra.model.Entity;

@Transactional(readOnly=true)
public abstract class AbstractRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	// ---------------- Protected Methods ---------------- //
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	protected SQLQuery createSQLQuery(String sql) {
		return createSQLQuery(sql, null);
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	protected SQLQuery createSQLQuery(StringBuilder sql) {
		return createSQLQuery(sql.toString());
	}
	
	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	protected SQLQuery createSQLQuery(String sql, Map<String, Object> parameters) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (parameters != null) {
			setParameters(sqlQuery, parameters);
		}
		return sqlQuery;
	}
	
	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	protected SQLQuery createSQLQuery(StringBuilder sql, Map<String, Object> parameters) {
		return createSQLQuery(sql.toString(), parameters);
	}
		
	/**
	 * 
	 * @param query
	 * @param parameters
	 */
	protected void setParameters(Query query, Map<String, Object> parameters) {
		String[] keys = query.getNamedParameters();
		if (keys != null) {
			for (String key: keys) {
				Object value = parameters.get(key);
				if (value != null) {
					if (value instanceof Entity) {
						query.setEntity(key, value);
					}
					else if (value.getClass().isArray()) {
						query.setParameterList(key, (Object[]) value);
					}
					else if (value instanceof Collection<?>) {
						query.setParameterList(key, (Collection<?>) value);
					}
					else {
						query.setParameter(key, value);
					}
				}
			}
		}
	}
	
	// ---------------- Public Methods ---------------- //
	
	/**
	 * 
	 */
	public void flush() {
		getSession().flush();
	}
}