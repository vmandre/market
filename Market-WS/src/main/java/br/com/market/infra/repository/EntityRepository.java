package br.com.market.infra.repository;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import br.com.market.infra.model.Entity;


@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class EntityRepository<T extends Entity> extends AbstractRepository {

	private Class<T> entityClass;
	
	// ---------------- Protected Methods ---------------- //
	
	/**
	 * 
	 * @return
	 */
	protected String getEntityName() {
		return getEntityClass().getName();
	}
	
	/**
	 * 
	 * @return
	 */
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	/**
	 * 
	 * @param hql
	 * @return
	 */
	protected Query createQuery(String hql) {
		return createQuery(hql, null);
	}
	
	/**
	 * 
	 * @param hql
	 * @return
	 */
	protected Query createQuery(StringBuilder hql) {
		return createQuery(hql.toString());
	}
	
	/**
	 * 
	 * @param hql
	 * @param parameters
	 * @return
	 */
	protected Query createQuery(String hql, Map<String, Object> parameters) {
		Query query = getSession().createQuery(hql);
		if (parameters != null) {
			setParameters(query, parameters);
		}
		return query;
	}
	
	/**
	 * 
	 * @param hql
	 * @param parameters
	 * @return
	 */
	protected Query createQuery(StringBuilder hql, Map<String, Object> parameters) {
		return createQuery(hql.toString(), parameters);
	}
	
	// ---------------- Public Methods ---------------- //
	
	/**
	 * 
	 * @param entity
	 */
	public void evict(T entity) {
		getSession().evict(entity);
	}
	
	/**
	 * 
	 * @param cod
	 * @return
	 */
	public T get(Long cod) {
		return (T) getSession().get(getEntityClass(), cod);
	}
	
	/**
	 * 
	 * @param cod
	 * @return
	 */
	public T load(Long cod) {
		return (T) getSession().load(getEntityClass(), cod);
	}
	
	public List<T> listAllOrderBy(String propertyOrder) {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName()).append(" ");
		hql	.append("order by " + propertyOrder);
		Query query = createQuery(hql);
		return query.list();
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getSession().save(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getSession().update(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	/**
	 * 
	 * @param cods
	 */
	public void loadAndDeleteByCod(Long...cods) {
		for (Long cod : cods) {
			T entity = load(cod);
			if (entity != null) {
				delete(entity);
			}
		}
	}
	
	/**
	 * 
	 * @param cods
	 */
	public void deleteByCod(Long...cods) {
		
		StringBuilder hql = new StringBuilder();
		
		hql	.append("delete ");
		hql	.append(	"from ").append(getEntityName()).append(" ");
		hql	.append("where cod ").append(cods.length == 1 ? "= :cod" : "in (:cods)");
		
		Query query = createQuery(hql);
		
		if (cods.length == 1) {
			query.setLong("cod", cods[0]);
		}
		
		else {
			query.setParameterList("cods", cods);
		}
		
		query.executeUpdate();
	}
	
	public T load(Long cod, String...fields) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select ");
		hql	.append(	"entity.cod as cod ");
		for (String field : fields) {
			hql	.append(", ");
			hql	.append("entity.").append(field).append(" as ").append(field);
		}
		hql	.append(" ");
		hql	.append("from ").append(getEntityName()).append(" entity ");
		hql	.append("where entity.cod = :cod ");
		Query query = createQuery(hql);
		query.setLong("cod", cod);
		query.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		return (T) query.uniqueResult();
	}
	
	public void update(T entity, String...fields) {
		
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		
		hql	.append("update ").append(getEntityName()).append(" set ");
		
		try {
			
			for (int i = 0; i < fields.length; i++) {
				
				String field = fields[i];
				
				if (i > 0) {
					hql	.append(", ");
				}
				
				hql	.append(field);
				hql	.append(" = ");
				
				Object object = PropertyUtils.getProperty(entity, field);
				if (object == null) {
					hql	.append("null");
				}
				else {
					hql	.append(":").append(field);
					params.put(field, object);
				}
				
				hql	.append(" ");
			}
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
		hql	.append("where cod = :cod ");
		params.put("cod", entity.getCod());
		
		Query query = createQuery(hql, params);
		
		query.executeUpdate();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<T> listAll() {
		StringBuilder hql = new StringBuilder();
		hql	.append("from ").append(getEntityName());
		Query query = createQuery(hql);
		return query.list();
	}
	
	/**
	 * 
	 * @param field
	 * @param value
	 * @param fields
	 * @return
	 */
	public T uniqueResult(String field, Object value, String...fields) {
		StringBuilder hql = new StringBuilder();
		String alias = "entity";
		String wildcard = field.replaceAll("\\.", "_");
		hql	.append("select ");
		for (int i = 0; i < fields.length; i++) {
			hql	.append(alias).append(".").append(fields[i]).append(" as ").append(fields[i]);
			if (i < (fields.length - 1)) {
				hql	.append(",");
			}
			hql	.append(" ");
		}
		hql	.append("from ").append(getEntityName()).append(" ").append(alias).append(" ");
		hql	.append("where ").append(alias).append(".").append(field).append(" = :").append(wildcard);
		Query query = createQuery(hql);
		query.setParameter(wildcard, value);
		query.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		return (T) query.uniqueResult();
	}
}