package it.unifi.swa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private final Class<E> type;

	@PersistenceContext
	protected EntityManager entityManager;

	protected BaseDao(Class<E> type) {
		this.type = type;
	}

	public E findById(int id) {
		return entityManager.find(type, id);
	}

	
	public void save(E entity) {

		entityManager.persist(entity);
	}
	
	public void update(E entity) {

		entityManager.merge(entity);
	}

	public void delete(E entity) {

		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
