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
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return (T) getSession().get(getEntityClass(), id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T load(Long id) {
		return (T) getSession().load(getEntityClass(), id);
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
	 * @param ids
	 */
	public void loadAndDeleteById(Long...ids) {
		for (Long id : ids) {
			T entity = load(id);
			if (entity != null) {
				delete(entity);
			}
		}
	}
	
	/**
	 * 
	 * @param ids
	 */
	public void deleteById(Long...ids) {
		
		StringBuilder hql = new StringBuilder();
		
		hql	.append("delete ");
		hql	.append(	"from ").append(getEntityName()).append(" ");
		hql	.append("where id ").append(ids.length == 1 ? "= :id" : "in (:ids)");
		
		Query query = createQuery(hql);
		
		if (ids.length == 1) {
			query.setLong("id", ids[0]);
		}
		
		else {
			query.setParameterList("ids", ids);
		}
		
		query.executeUpdate();
	}
	
	public T load(Long id, String...fields) {
		StringBuilder hql = new StringBuilder();
		hql	.append("select ");
		hql	.append(	"entity.id as id ");
		for (String field : fields) {
			hql	.append(", ");
			hql	.append("entity.").append(field).append(" as ").append(field);
		}
		hql	.append(" ");
		hql	.append("from ").append(getEntityName()).append(" entity ");
		hql	.append("where entity.id = :id ");
		Query query = createQuery(hql);
		query.setLong("id", id);
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
		
		hql	.append("where id = :id ");
		params.put("id", entity.getId());
		
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