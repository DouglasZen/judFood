package br.com.wsfood.prato;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.wsfood.base.BaseDAO;


public class PratoDAO extends BaseDAO<Prato>{

	@Override
	protected Class<Prato> getEntityClass() {
		return Prato.class;
	}
	
	public List<Prato> listarPratos(){
		session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(getEntityClass());
		List<Prato> pratos = c.list();
		return pratos;
	}
	
	public Prato getPrato(int id){
		session = (Session) em.getDelegate();
		Prato prato = (Prato) session.get(Prato.class, id);
		return prato;
	}

	
}
