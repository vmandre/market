package br.com.market.infra.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.market.infra.model.Entity;
import br.com.market.infra.repository.EntityRepository;

public abstract class EntityService<T extends Entity<?>> {
	
	protected final EntityRepository<T> repository;
	
	public EntityService(EntityRepository<T> repository) {
		this.repository = repository;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void flush() {
		repository.flush();
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void evict(T entity) {
		repository.evict(entity);
	}
	
	/**
	 * 
	 * @param cod
	 * @return
	 */
	public T get(Long cod) {
		return repository.get(cod);
	}
	
	/**
	 * 
	 * @param cod
	 * @return
	 */
	public T load(Long cod) {
		return repository.load(cod);
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T merge(T entity) {
		return repository.merge(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void persist(T entity) {
		repository.persist(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(T entity) {
		repository.save(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	public void update(T entity) {
		repository.update(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(T entity) {
		repository.saveOrUpdate(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(T entity) {
		repository.delete(entity);
	}
	
	/**
	 * 
	 * @param cods
	 */
	@Transactional(rollbackFor=Exception.class)
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
	@Transactional(rollbackFor=Exception.class)
	public void deleteByCod(Long...cods) {
		repository.deleteByCod(cods);
	}
	
	/**
	 * 
	 * @param entity
	 * @param fields
	 */
	@Transactional(rollbackFor=Exception.class)
	public void update(T entity, String...fields) {
		repository.update(entity, fields);
	}
	
	/**
	 * Recupera registro pelo código.
	 * 
	 * @param cod
	 * @param fields
	 * @return
	 */
	public T load(Long cod, String...fields) {
		return repository.load(cod, fields);
	}
	
	/**
	 * Lista todos registros ordenado pelos campos informados.
	 * 
	 * @param propertyOrder
	 * @return
	 */
	public List<T> listAllOrderBy(String propertyOrder) {
		return repository.listAllOrderBy(propertyOrder);
	}
	
	/**
	 * 
	 * @param field
	 * @param value
	 * @param fields
	 * @return
	 */
	public T uniqueResult(String field, Object value, String...fields) {
		return repository.uniqueResult(field, value, fields);
	}
	
	/**
	 * Lista todos registro do banco.
	 * 
	 * @param order
	 * @return
	 */
	public List<T> listAll() {
		return repository.listAll();
	}
}