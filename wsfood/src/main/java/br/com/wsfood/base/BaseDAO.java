package br.com.wsfood.base;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Scope;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public abstract class BaseDAO<T> {
	
	
	
	
	
	protected EntityManagerFactory factory;
	protected EntityManager em;
	protected Session session;
	
	public BaseDAO(){
		this.factory = Persistence.createEntityManagerFactory("default");
		this.em = this.factory.createEntityManager();
	}
	
	
	
	protected Criteria createCriteria() {
		return session.createCriteria(getEntityClass());
	}
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) session.get(getEntityClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return createCriteria().list();
	}
	
	public void save(T entity) throws Exception {
		session.saveOrUpdate(entity);
		session.flush();
	}
	
	public void merge(T entity)throws Exception {
		session.merge(entity);
		session.flush();
	}
	
	public void delete(T entity) {
		session.delete(entity);
		session.flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listByCriteria(Criterion... criterions) {
		Criteria crit = session.createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			crit.add(c);
		}
		return crit.list();
	}
	
	protected abstract Class<T> getEntityClass();
}
